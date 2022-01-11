//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "cmd.hpp"
#include "task.hpp"

#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>

#include <lib/app/cli_base.hpp>
#include <utils/common.hpp>
#include <utils/io.hpp>
#include <utils/libc_error.hpp>
#include <utils/options.hpp>
#include <utils/random.hpp>

#define PAUSE_CONFIRM_TIMEOUT 3000
#define PAUSE_POLLING 10

// todo add coverage again to cli
static std::string SignalDescription(int dbm)
{
    if (dbm > -90)
        return "Excellent";
    if (dbm > -105)
        return "Good";
    if (dbm > -120)
        return "Fair";
    return "Poor";
}

namespace nr::ue
{

void UeCmdHandler::onStart()
{
    if (!m_ue->config->disableCmd)
    {
        io::CreateDirectory(cons::CLI_SOCKET_DIR);
        std::string socketName = cons::CLI_SOCKET_DIR + m_ue->config->getNodeName();

        struct sockaddr_un name = {};
        int fd = socket(AF_UNIX, SOCK_DGRAM, 0);
        if (fd < 0)
            throw LibError("Could not open domain socket");

        unlink(socketName.c_str());

        name.sun_family = AF_UNIX;
        strncpy(name.sun_path, socketName.c_str(), sizeof(name.sun_path) - 1);

        int ret = bind(fd, (const struct sockaddr *)&name, sizeof(name));
        if (ret < 0)
            throw LibError("Could not bind domain socket");

        io::RelaxPermissions(socketName);

        m_ue->fdBase->allocate(FdBase::CLI, fd);
    }
}

void UeCmdHandler::receiveCmd(const InetAddress &address, const uint8_t *buffer, size_t size)
{
    OctetView v{buffer, static_cast<size_t>(size)};
    if (v.readI() != cons::Major)
        return;
    if (v.readI() != cons::Minor)
        return;
    if (v.readI() != cons::Patch)
        return;

    app::CliMessage cliMessage{};
    cliMessage.type = static_cast<app::CliMessage::Type>(v.readI());
    int nodeNameLength = v.read4I();
    cliMessage.nodeName = v.readUtf8String(nodeNameLength);
    int valueLength = v.read4I();
    cliMessage.value = v.readUtf8String(valueLength);
    cliMessage.clientAddr = address;

    if (cliMessage.type == app::CliMessage::Type::ECHO)
    {
        sendMessage(cliMessage);
        return;
    }

    if (cliMessage.type != app::CliMessage::Type::COMMAND)
        return;

    if (cliMessage.value.size() > 0xFFFF)
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, "Command is too large"));
        return;
    }

    if (cliMessage.nodeName.size() > 0xFFFF)
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, "Node name is too large"));
        return;
    }

    if (cliMessage.value.empty())
    {
        sendMessage(app::CliMessage::Result(cliMessage.clientAddr, ""));
        return;
    }

    std::vector<std::string> tokens{};

    auto exp = opt::PerformExpansion(cliMessage.value, tokens);
    if (exp != opt::ExpansionResult::SUCCESS)
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, "Invalid command: " + cliMessage.value));
        return;
    }

    if (tokens.empty())
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, "Empty command"));
        return;
    }

    std::string error{}, output{};
    auto cmd = app::ParseUeCliCommand(std::move(tokens), error, output);
    if (!error.empty())
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, error));
        return;
    }
    if (!output.empty())
    {
        sendMessage(app::CliMessage::Result(cliMessage.clientAddr, output));
        return;
    }
    if (cmd == nullptr)
    {
        sendMessage(app::CliMessage::Error(cliMessage.clientAddr, ""));
        return;
    }

    handleCmd(cliMessage.clientAddr, std::move(cmd));
}

void UeCmdHandler::sendResult(const InetAddress &address, const std::string &output)
{
    sendMessage(app::CliMessage::Result(address, output, ""));
}

void UeCmdHandler::sendError(const InetAddress &address, const std::string &output)
{
    sendMessage(app::CliMessage::Error(address, output, ""));
}

void UeCmdHandler::sendMessage(const app::CliMessage &msg)
{
    OctetString stream;
    app::CliMessage::Encode(msg, stream);

    m_ue->fdBase->sendTo(FdBase::CLI, stream.data(), static_cast<size_t>(stream.length()), msg.clientAddr);
}

void UeCmdHandler::handleCmd(const InetAddress &address, std::unique_ptr<app::UeCliCommand> &&cmd)
{
    switch (cmd->present)
    {
    case app::UeCliCommand::STATUS: {
        std::optional<int> currentCellId = std::nullopt;
        std::optional<Plmn> currentPlmn = std::nullopt;
        std::optional<int> currentTac = std::nullopt;

        auto &currentCell = m_ue->shCtx.currentCell;
        if (currentCell.hasValue())
        {
            currentCellId = currentCell.cellId;
            currentPlmn = currentCell.plmn;
            currentTac = currentCell.tac;
        }

        Json json = Json::Obj({
            {"cm-state", ToJson(m_ue->nas->m_mm->m_cmState)},
            {"rm-state", ToJson(m_ue->nas->m_mm->m_rmState)},
            {"mm-state", ToJson(m_ue->nas->m_mm->m_mmSubState)},
            {"5u-state", ToJson(m_ue->nas->m_mm->m_storage->uState->get())},
            {"sim-inserted", m_ue->nas->m_mm->m_usim->isValid()},
            {"selected-plmn", ::ToJson(m_ue->shCtx.selectedPlmn)},
            {"current-cell", ::ToJson(currentCellId)},
            {"current-plmn", ::ToJson(currentPlmn)},
            {"current-tac", ::ToJson(currentTac)},
            {"last-tai", ToJson(m_ue->nas->m_mm->m_storage->lastVisitedRegisteredTai)},
            {"stored-suci", ToJson(m_ue->nas->m_mm->m_storage->storedSuci->get())},
            {"stored-guti", ToJson(m_ue->nas->m_mm->m_storage->storedGuti->get())},
            {"has-emergency", ::ToJson(m_ue->nas->m_mm->hasEmergency())},
        });
        sendResult(address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::INFO: {
        auto json = Json::Obj({
            {"supi", ToJson(m_ue->config->supi)},
            {"hplmn", ToJson(m_ue->config->hplmn)},
            {"imei", ::ToJson(m_ue->config->imei)},
            {"imeisv", ::ToJson(m_ue->config->imeiSv)},
            {"ecall-only", ::ToJson(m_ue->nas->m_usim->m_isECallOnly)},
            {"uac-aic", Json::Obj({
                            {"mps", m_ue->config->uacAic.mps},
                            {"mcs", m_ue->config->uacAic.mcs},
                        })},
            {"uac-acc", Json::Obj({
                            {"normal-class", m_ue->config->uacAcc.normalCls},
                            {"class-11", m_ue->config->uacAcc.cls11},
                            {"class-12", m_ue->config->uacAcc.cls12},
                            {"class-13", m_ue->config->uacAcc.cls13},
                            {"class-14", m_ue->config->uacAcc.cls14},
                            {"class-15", m_ue->config->uacAcc.cls15},
                        })},
            {"is-high-priority", m_ue->nas->m_mm->isHighPriority()},
        });

        sendResult(address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::TIMERS: {
        sendResult(address, ToJson(m_ue->nas->m_timers).dumpYaml());
        break;
    }
    case app::UeCliCommand::DE_REGISTER: {
        m_ue->nas->m_mm->deregistrationRequired(cmd->deregCause);

        if (cmd->deregCause != EDeregCause::SWITCH_OFF)
            sendResult(address, "De-registration procedure triggered");
        else
            sendResult(address, "De-registration procedure triggered. UE device will be switched off.");
        break;
    }
    case app::UeCliCommand::PS_RELEASE: {
        for (int i = 0; i < cmd->psCount; i++)
            m_ue->nas->m_sm->sendReleaseRequest(static_cast<int>(cmd->psIds[i]) % 16);
        sendResult(address, "PDU session release procedure(s) triggered");
        break;
    }
    case app::UeCliCommand::PS_RELEASE_ALL: {
        m_ue->nas->m_sm->sendReleaseRequestForAll();
        sendResult(address, "PDU session release procedure(s) triggered");
        break;
    }
    case app::UeCliCommand::PS_ESTABLISH: {
        SessionConfig config;
        config.type = nas::EPduSessionType::IPV4;
        config.isEmergency = cmd->isEmergency;
        config.apn = cmd->apn;
        config.sNssai = cmd->sNssai;
        m_ue->nas->m_sm->sendEstablishmentRequest(config);
        sendResult(address, "PDU session establishment procedure triggered");
        break;
    }
    case app::UeCliCommand::PS_LIST: {
        Json json = Json::Obj({});
        for (auto *pduSession : m_ue->nas->m_sm->m_pduSessions)
        {
            if (pduSession->psi == 0 || pduSession->psState == EPsState::INACTIVE)
                continue;

            auto obj = Json::Obj({
                {"state", ToJson(pduSession->psState)},
                {"session-type", ToJson(pduSession->sessionType)},
                {"apn", ::ToJson(pduSession->apn)},
                {"s-nssai", ToJson(pduSession->sNssai)},
                {"emergency", pduSession->isEmergency},
                {"address", ::ToJson(pduSession->pduAddress)},
                {"ambr", ::ToJson(pduSession->sessionAmbr)},
                {"data-pending", pduSession->uplinkPending},
            });

            json.put("PDU Session" + std::to_string(pduSession->psi), obj);
        }
        sendResult(address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::RLS_STATE: {
        Json json = Json::Obj({
            {"sti", OctetString::FromOctet8(m_ue->shCtx.sti).toHexString()},
            {"gnb-search-space", ::ToJson(m_ue->config->gnbSearchList)},
        });
        sendResult(address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::COVERAGE: {
        Json json = Json::Obj({});

        const auto &cells = m_ue->rrc->m_cellDesc;
        for (auto &item : cells)
        {
            auto &cell = item.second;

            auto mib = Json{};
            auto sib1 = Json{};

            if (cell.mib.hasMib)
            {
                mib = Json::Obj({
                    {"barred", cell.mib.isBarred},
                    {"intra-freq-reselection",
                     std::string{cell.mib.isIntraFreqReselectAllowed ? "allowed" : "not-allowed"}},
                });
            }
            if (cell.sib1.hasSib1)
            {
                sib1 = Json::Obj({
                    {"nr-cell-id", utils::IntToHex(cell.sib1.nci)},
                    {"plmn", ToJson(cell.sib1.plmn)},
                    {"tac", cell.sib1.tac},
                    {"operator-reserved", cell.sib1.isReserved},
                });
            }

            auto obj = Json::Obj({{"signal", std::to_string(cell.dbm) + " dBm (" + SignalDescription(cell.dbm) + ")"},
                                  {"mib", mib},
                                  {"sib1", sib1}});

            json.put("[" + std::to_string(item.first) + "]", obj);
        }

        if (cells.empty())
            json = "No cell available";

        sendResult(address, json.dumpYaml());
        break;
    }
    }
}

} // namespace nr::ue

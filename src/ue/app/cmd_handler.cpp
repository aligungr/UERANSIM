//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "cmd_handler.hpp"

#include <ue/app/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/rls/task.hpp>
#include <ue/rrc/task.hpp>
#include <ue/tun/task.hpp>
#include <utils/common.hpp>
#include <utils/printer.hpp>

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

void UeCmdHandler::sendResult(const InetAddress &address, const std::string &output)
{
    m_base->cliCallbackTask->push(std::make_unique<app::NwCliSendResponse>(address, output, false));
}

void UeCmdHandler::sendError(const InetAddress &address, const std::string &output)
{
    m_base->cliCallbackTask->push(std::make_unique<app::NwCliSendResponse>(address, output, true));
}

void UeCmdHandler::pauseTasks()
{
    m_base->nasTask->requestPause();
    m_base->rrcTask->requestPause();
    m_base->rlsTask->requestPause();
}

void UeCmdHandler::unpauseTasks()
{
    m_base->nasTask->requestUnpause();
    m_base->rrcTask->requestUnpause();
    m_base->rlsTask->requestUnpause();
}

bool UeCmdHandler::isAllPaused()
{
    if (!m_base->nasTask->isPauseConfirmed())
        return false;
    if (!m_base->rrcTask->isPauseConfirmed())
        return false;
    if (!m_base->rlsTask->isPauseConfirmed())
        return false;
    return true;
}

void UeCmdHandler::handleCmd(NmUeCliCommand &msg)
{
    pauseTasks();

    uint64_t currentTime = utils::CurrentTimeMillis();
    uint64_t endTime = currentTime + PAUSE_CONFIRM_TIMEOUT;

    bool isPaused = false;
    while (currentTime < endTime)
    {
        currentTime = utils::CurrentTimeMillis();
        if (isAllPaused())
        {
            isPaused = true;
            break;
        }
        utils::Sleep(PAUSE_POLLING);
    }

    if (!isPaused)
    {
        sendError(msg.address, "UE is unable process command due to pausing timeout");
    }
    else
    {
        handleCmdImpl(msg);
    }

    unpauseTasks();
}

void UeCmdHandler::handleCmdImpl(NmUeCliCommand &msg)
{
    switch (msg.cmd->present)
    {
    case app::UeCliCommand::STATUS: {
        std::optional<int> currentCellId = std::nullopt;
        std::optional<Plmn> currentPlmn = std::nullopt;
        std::optional<int> currentTac = std::nullopt;

        auto currentCell = m_base->shCtx.currentCell.get();
        if (currentCell.hasValue())
        {
            currentCellId = currentCell.cellId;
            currentPlmn = currentCell.plmn;
            currentTac = currentCell.tac;
        }

        Json json = Json::Obj({
            {"cm-state", ToJson(m_base->nasTask->mm->m_cmState)},
            {"rm-state", ToJson(m_base->nasTask->mm->m_rmState)},
            {"mm-state", ToJson(m_base->nasTask->mm->m_mmSubState)},
            {"5u-state", ToJson(m_base->nasTask->mm->m_storage->uState->get())},
            {"sim-inserted", m_base->nasTask->mm->m_usim->isValid()},
            {"selected-plmn", ::ToJson(m_base->shCtx.selectedPlmn.get())},
            {"current-cell", ::ToJson(currentCellId)},
            {"current-plmn", ::ToJson(currentPlmn)},
            {"current-tac", ::ToJson(currentTac)},
            {"last-tai", ToJson(m_base->nasTask->mm->m_storage->lastVisitedRegisteredTai)},
            {"stored-suci", ToJson(m_base->nasTask->mm->m_storage->storedSuci->get())},
            {"stored-guti", ToJson(m_base->nasTask->mm->m_storage->storedGuti->get())},
            {"has-emergency", ::ToJson(m_base->nasTask->mm->hasEmergency())},
        });
        sendResult(msg.address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::INFO: {
        auto json = Json::Obj({
            {"supi", ToJson(m_base->config->supi)},
            {"hplmn", ToJson(m_base->config->hplmn)},
            {"imei", ::ToJson(m_base->config->imei)},
            {"imeisv", ::ToJson(m_base->config->imeiSv)},
            {"ecall-only", ::ToJson(m_base->nasTask->usim->m_isECallOnly)},
            {"uac-aic", Json::Obj({
                            {"mps", m_base->config->uacAic.mps},
                            {"mcs", m_base->config->uacAic.mcs},
                        })},
            {"uac-acc", Json::Obj({
                            {"normal-class", m_base->config->uacAcc.normalCls},
                            {"class-11", m_base->config->uacAcc.cls11},
                            {"class-12", m_base->config->uacAcc.cls12},
                            {"class-13", m_base->config->uacAcc.cls13},
                            {"class-14", m_base->config->uacAcc.cls14},
                            {"class-15", m_base->config->uacAcc.cls15},
                        })},
            {"is-high-priority", m_base->nasTask->mm->isHighPriority()},
        });

        sendResult(msg.address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::TIMERS: {
        sendResult(msg.address, ToJson(m_base->nasTask->timers).dumpYaml());
        break;
    }
    case app::UeCliCommand::DE_REGISTER: {
        m_base->nasTask->mm->deregistrationRequired(msg.cmd->deregCause);

        if (msg.cmd->deregCause != EDeregCause::SWITCH_OFF)
            sendResult(msg.address, "De-registration procedure triggered");
        else
            sendResult(msg.address, "De-registration procedure triggered. UE device will be switched off.");
        break;
    }
    case app::UeCliCommand::PS_RELEASE: {
        for (int i = 0; i < msg.cmd->psCount; i++)
            m_base->nasTask->sm->sendReleaseRequest(static_cast<int>(msg.cmd->psIds[i]) % 16);
        sendResult(msg.address, "PDU session release procedure(s) triggered");
        break;
    }
    case app::UeCliCommand::PS_RELEASE_ALL: {
        m_base->nasTask->sm->sendReleaseRequestForAll();
        sendResult(msg.address, "PDU session release procedure(s) triggered");
        break;
    }
    case app::UeCliCommand::PS_ESTABLISH: {
        SessionConfig config;
        config.type = nas::EPduSessionType::IPV4;
        config.isEmergency = msg.cmd->isEmergency;
        config.apn = msg.cmd->apn;
        config.sNssai = msg.cmd->sNssai;
        m_base->nasTask->sm->sendEstablishmentRequest(config);
        sendResult(msg.address, "PDU session establishment procedure triggered");
        break;
    }
    case app::UeCliCommand::PS_LIST: {
        Json json = Json::Obj({});
        for (auto *pduSession : m_base->nasTask->sm->m_pduSessions)
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
        sendResult(msg.address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::RLS_STATE: {
        Json json = Json::Obj({
            {"sti", OctetString::FromOctet8(m_base->rlsTask->m_shCtx->sti).toHexString()},
            {"gnb-search-space", ::ToJson(m_base->config->gnbSearchList)},
        });
        sendResult(msg.address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::COVERAGE: {
        Json json = Json::Obj({});

        const auto &cells = m_base->rrcTask->m_cellDesc;
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

        sendResult(msg.address, json.dumpYaml());
        break;
    }
    }
}

} // namespace nr::ue

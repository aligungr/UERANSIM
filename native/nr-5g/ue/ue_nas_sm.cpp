//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"
#include <nas_proto_conf.hpp>
#include <nas_utils.hpp>

namespace nr::ue
{

int NasTask::allocatePduSessionId()
{
    auto &arr = smCtx.pduSessions;

    int id = -1;
    for (int i = PduSession::MIN_ID; i <= PduSession::MAX_ID; i++)
    {
        if (arr[i].id == 0)
        {
            id = i;
            break;
        }
    }

    if (id == -1)
    {
        logger->err("PDU session allocation failed");
        return 0;
    }

    arr[id] = {};
    arr[id].id = id;
    arr[id].isEstablished = false;

    logger->debug("PDU session allocated: %d", id);
    return id;
}

int NasTask::allocateProcedureTransactionId()
{
    auto &arr = smCtx.procedureTransactions;

    int id = -1;
    for (int i = ProcedureTransaction::MIN_ID; i <= ProcedureTransaction::MAX_ID; i++)
    {
        if (arr[i].id == 0)
        {
            id = i;
            break;
        }
    }

    if (id == -1)
    {
        logger->err("PTI allocation failed");
        return 0;
    }

    arr[id] = {};
    arr[id].id = id;

    logger->debug("PTI allocated: %d", id);
    return id;
}

void NasTask::releaseProcedureTransactionId(int pti)
{
    smCtx.procedureTransactions[pti].id = 0;
    logger->debug("PTI released: %d", pti);
}

void NasTask::releasePduSession(int psi)
{
    smCtx.pduSessions[psi].id = 0;
    logger->info("PDU session released: %d", psi);
}

void NasTask::sendEstablishmentRequest()
{
    int psi = allocatePduSessionId();
    if (psi == 0)
    {
        logger->err("PDU Session Establishment Request could not send");
        return;
    }

    int pti = allocateProcedureTransactionId();
    if (pti == 0)
    {
        logger->err("PDU Session Establishment Request could not send");
        releasePduSession(psi);
        return;
    }

    // TODO
    nas::ProtocolConfigurationOptions opt;
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_UP_IP_ADDRESS_ALLOCATION_VIA_NAS_SIGNALLING, true, OctetString::Empty()));
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_DOWN_DNS_SERVER_IPV4_ADDRESS, true, OctetString::Empty()));

    // TODO
    nas::PduSessionEstablishmentRequest req;
    req.pti = pti;
    req.pduSessionId = static_cast<nas::EPduSessionIdentity>(psi);
    req.integrityProtectionMaximumDataRate.maxRateUplink =
        nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink::FULL_DATA_RATE;
    req.integrityProtectionMaximumDataRate.maxRateDownlink =
        nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink::FULL_DATA_RATE;
    req.pduSessionType = nas::IEPduSessionType{};
    req.pduSessionType->pduSessionType = nas::EPduSessionType::IPV4;
    req.sscMode = nas::IESscMode{};
    req.sscMode->sscMode = nas::ESscMode::SSC_MODE_1;
    req.extendedProtocolConfigurationOptions = nas::IEExtendedProtocolConfigurationOptions{};
    req.extendedProtocolConfigurationOptions->configurationProtocol = nas::EConfigurationProtocol::PPP;
    req.extendedProtocolConfigurationOptions->extension = true;
    req.extendedProtocolConfigurationOptions->options = opt.encode();

    timers.t3580.start();
    sendSmMessage(pti, req);
}

void NasTask::receivePduSessionEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg)
{
    if (msg.smCause.has_value())
    {
        logger->warn("SM cause received in PduSessionEstablishmentAccept: %s",
                     nas::utils::EnumToString(msg.smCause->value));
    }

    timers.t3580.stop();

    releaseProcedureTransactionId(msg.pti);

    auto &pduSession = smCtx.pduSessions[static_cast<int>(msg.pduSessionId)];
    if (pduSession.id == 0)
        logger->err("PDU session not found: %d", msg.pduSessionId);

    pduSession.isEstablished = true;
    pduSession.authorizedQoSRules = nas::utils::DeepCopyIe(msg.authorizedQoSRules);
    pduSession.sessionAmbr = nas::utils::DeepCopyIe(msg.sessionAmbr);
    pduSession.sessionType = nas::utils::DeepCopyIe(msg.selectedPduSessionType);

    if (msg.authorizedQoSFlowDescriptions.has_value())
        pduSession.authorizedQoSFlowDescriptions = nas::utils::DeepCopyIe(*msg.authorizedQoSFlowDescriptions);
    else
        pduSession.authorizedQoSFlowDescriptions = {};

    if (msg.pduAddress.has_value())
        pduSession.pduAddress = nas::utils::DeepCopyIe(*msg.pduAddress);
    else
        pduSession.pduAddress = {};

    // TODO status update

    logger->debug("PDU session established: %s", pduSession.id);
    logger->info("PDU Session Establishment is successful");
}

void NasTask::receivePduSessionEstablishmentReject(const nas::PduSessionEstablishmentReject &msg)
{
    logger->err("PDU Session Establishment Reject received (%s)", nas::utils::EnumToString(msg.smCause.value));
    // TODO
}

} // namespace nr::ue
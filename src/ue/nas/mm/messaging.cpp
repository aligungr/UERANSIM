//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/enc.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/rrc/task.hpp>

//state learner
#include <ue/app/state_learner.hpp>
#include <lib/nas/nas_mutator.hpp>

namespace nr::ue
{

int FLAG_RECV; // 0: normal, 1: not_protected, 2: replayed

// helper function for setting security header
void set_header(nas::PlainMmMessage &msg, int type)
{
    if (type == 1)
        msg.sht = nas::ESecurityHeaderType::NOT_PROTECTED;
    else if (type == 2)
        msg.sht = nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED;
}

static bool IsInitialNasMessage(const nas::PlainMmMessage &msg)
{
    auto msgType = msg.messageType;
    return msgType == nas::EMessageType::REGISTRATION_REQUEST ||
           msgType == nas::EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING ||
           msgType == nas::EMessageType::SERVICE_REQUEST;
}

static bool IsAcceptedWithoutIntegrity(const nas::PlainMmMessage &msg)
{
    auto msgType = msg.messageType;
    return msgType == nas::EMessageType::IDENTITY_REQUEST || msgType == nas::EMessageType::AUTHENTICATION_REQUEST ||
           msgType == nas::EMessageType::AUTHENTICATION_RESULT || msgType == nas::EMessageType::AUTHENTICATION_REJECT ||
           msgType == nas::EMessageType::REGISTRATION_REJECT ||
           msgType == nas::EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING ||
           msgType == nas::EMessageType::SERVICE_REJECT;
}

static bool HasNonCleartext(const nas::PlainMmMessage &msg)
{
    if (msg.messageType == nas::EMessageType::REGISTRATION_REQUEST)
    {
        auto &regReq = (nas::RegistrationRequest &)(msg);
        return regReq.nasMessageContainer || regReq.nonCurrentNgKsi || regReq.micoIndication ||
               regReq.networkSlicingIndication || regReq.mmCapability || regReq.requestedNSSAI ||
               regReq.requestedDrxParameters || regReq.uesUsageSetting || regReq.updateType ||
               regReq.uplinkDataStatus || regReq.allowedPduSessionStatus || regReq.lastVisitedRegisteredTai ||
               regReq.s1UeNetworkCapability || regReq.pduSessionStatus || regReq.payloadContainer ||
               regReq.ladnIndication;
    }
    else if (msg.messageType == nas::EMessageType::SERVICE_REQUEST)
    {
        auto &servReq = (nas::ServiceRequest &)(msg);
        return servReq.nasMessageContainer || servReq.uplinkDataStatus || servReq.pduSessionStatus ||
               servReq.allowedPduSessionStatus;
    }
    return false;
}

static void RemoveCleartextIEs(nas::PlainMmMessage &msg, OctetString &&nasMsgContainer)
{
    if (msg.messageType == nas::EMessageType::REGISTRATION_REQUEST)
    {
        auto &regReq = (nas::RegistrationRequest &)(msg);

        if (nasMsgContainer.length() == 0)
            regReq.nasMessageContainer = std::nullopt;
        else
        {
            regReq.nasMessageContainer = nas::IENasMessageContainer{};
            regReq.nasMessageContainer->data = std::move(nasMsgContainer);
        }

        regReq.nonCurrentNgKsi = std::nullopt;
        regReq.micoIndication = std::nullopt;
        regReq.networkSlicingIndication = std::nullopt;
        regReq.mmCapability = std::nullopt;
        regReq.requestedNSSAI = std::nullopt;
        regReq.requestedDrxParameters = std::nullopt;
        regReq.uesUsageSetting = std::nullopt;
        regReq.updateType = std::nullopt;
        regReq.uplinkDataStatus = std::nullopt;
        regReq.allowedPduSessionStatus = std::nullopt;
        regReq.lastVisitedRegisteredTai = std::nullopt;
        regReq.s1UeNetworkCapability = std::nullopt;
        regReq.pduSessionStatus = std::nullopt;
        regReq.payloadContainer = std::nullopt;
        regReq.ladnIndication = std::nullopt;
    }
    else if (msg.messageType == nas::EMessageType::SERVICE_REQUEST)
    {
        auto &servReq = (nas::ServiceRequest &)(msg);

        if (nasMsgContainer.length() == 0)
            servReq.nasMessageContainer = std::nullopt;
        else
        {
            servReq.nasMessageContainer = nas::IENasMessageContainer{};
            servReq.nasMessageContainer->data = std::move(nasMsgContainer);
        }

        servReq.uplinkDataStatus = std::nullopt;
        servReq.pduSessionStatus = std::nullopt;
        servReq.allowedPduSessionStatus = std::nullopt;
    }
}

EProcRc NasMm::sendNasMessage(const nas::PlainMmMessage &msg)
{
    // if (!m_base->shCtx.hasActiveCell())
    // {
    //     m_logger->debug("NAS Transport aborted, no active cell");
    //     return EProcRc::STAY;
    // }

    // if (m_cmState == ECmState::CM_IDLE && !IsInitialNasMessage(msg))
    // {
    //     m_logger->warn("NAS Transport aborted, Service Request is needed for uplink signalling");
    //     if (m_mmState != EMmState::MM_SERVICE_REQUEST_INITIATED)
    //         serviceRequestRequiredForSignalling();
    //     return EProcRc::STAY;
    // }

    bool hasNsCtx =
        m_usim->m_currentNsCtx && (m_usim->m_currentNsCtx->integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0 ||
                                   m_usim->m_currentNsCtx->ciphering != nas::ETypeOfCipheringAlgorithm::EA0);

    OctetString pdu;
    if (hasNsCtx)
    {
        if (m_usim->m_currentNsCtx->uplinkCount.sqn == 0xFF &&
            static_cast<int>(m_usim->m_currentNsCtx->uplinkCount.overflow) == 0xFFFF)
        {
            m_logger->warn("Uplink NAS Count about to wrap around, performing local release of NAS connection and "
                           "deleting current NSC");
            m_usim->m_currentNsCtx = nullptr;
            localReleaseConnection(false);
            return EProcRc::STAY;
        }

        if (msg.messageType == nas::EMessageType::REGISTRATION_REQUEST ||
            msg.messageType == nas::EMessageType::SERVICE_REQUEST)
        {
            if (FLAG_SECMOD == 1) // send no security message
            {
                auto plain_copy = nas::utils::DeepCopyMsg(msg);
                set_header((nas::PlainMmMessage &) *plain_copy, 1);
                nas::EncodeNasMessage(*plain_copy, pdu);
            }
            else if (FLAG_SECMOD == 2) // send integrity only message
            {
                auto copy = nas::utils::DeepCopyMsg(msg);
                if (HasNonCleartext(msg))
                {
                    auto temporary =
                        nas_enc::Encrypt(*m_usim->m_currentNsCtx, (nas::PlainMmMessage &)*copy, false, false);
                    m_usim->m_currentNsCtx->rollbackCountOnEncrypt();
                    auto content = temporary->plainNasMessage.copy();
                    RemoveCleartextIEs((nas::PlainMmMessage &)*copy, std::move(content));
                }
                auto copySecured = nas_enc::Encrypt(*m_usim->m_currentNsCtx, (nas::PlainMmMessage &)*copy, true, true);
                nas::EncodeNasMessage(*copySecured, pdu);
            }
            else if (FLAG_SECMOD == 3) // send integrity and ciphering message
            {
                auto encrypted = nas_enc::Encrypt(*m_usim->m_currentNsCtx, msg, false, false);
                nas::EncodeNasMessage(*encrypted, pdu);
            }
            else // default
            {
                if (m_cmState == ECmState::CM_IDLE)
                {
                    auto copy = nas::utils::DeepCopyMsg(msg);
                    if (HasNonCleartext(msg))
                    {
                        auto tmpMsg = nas_enc::Encrypt(*m_usim->m_currentNsCtx, (nas::PlainMmMessage &)*copy, false, false);
                        m_usim->m_currentNsCtx->rollbackCountOnEncrypt();
                        auto content = tmpMsg->plainNasMessage.copy();
                        RemoveCleartextIEs((nas::PlainMmMessage &)*copy, std::move(content));
                    }
                    auto copySecured = nas_enc::Encrypt(*m_usim->m_currentNsCtx, (nas::PlainMmMessage &)*copy, true, true);
                    nas::EncodeNasMessage(*copySecured, pdu);
                }
                else
                {
                    auto encrypted = nas_enc::Encrypt(*m_usim->m_currentNsCtx, msg, false, false);
                    nas::EncodeNasMessage(*encrypted, pdu);
                }
            }
        }
        // else if (msg.messageType == nas::EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING)
        // {
        //     if (m_cmState == ECmState::CM_IDLE)
        //     {
        //         auto encrypted = nas_enc::Encrypt(*m_usim->m_currentNsCtx, msg, true, true);
        //         nas::EncodeNasMessage(*encrypted, pdu);
        //     }
        //     else
        //     {
        //         auto encrypted = nas_enc::Encrypt(*m_usim->m_currentNsCtx, msg, false, false);
        //         nas::EncodeNasMessage(*encrypted, pdu);
        //     }
        // }
        else
        {
            if (FLAG_SECMOD == 1) // send no security message
            {
                auto copy = nas::utils::DeepCopyMsg(msg);
                if (!state_learner->enableFuzzing)
                    RemoveCleartextIEs((nas::PlainMmMessage &)*copy, {});
                nas::EncodeNasMessage(*copy, pdu);
            }
            else if (FLAG_SECMOD == 2) // send integrity only message
            {
                auto plain_copy = nas::utils::DeepCopyMsg(msg);
                auto encrypted_copy = nas_enc::Encrypt(*m_usim->m_currentNsCtx, (nas::PlainMmMessage &) *plain_copy, true, true);
                nas::EncodeNasMessage(*encrypted_copy, pdu);
            }
            else // default or FLAG_SECMOD == 3, send integrity and ciphering message
            {
                auto encrypted = nas_enc::Encrypt(*m_usim->m_currentNsCtx, msg, false, false);
                nas::EncodeNasMessage(*encrypted, pdu);
            }

        }
    }
    else
    {
        if (FLAG_SECMOD > 1)
        {
            state_learner->notify_response("null_action");
            return EProcRc::STAY;
        }
        if (IsInitialNasMessage(msg) && !state_learner->enableFuzzing) // do not remove cleartext IE for initial message when fuzzing
        {
            auto copy = nas::utils::DeepCopyMsg(msg);
            RemoveCleartextIEs((nas::PlainMmMessage &)*copy, {});

            nas::EncodeNasMessage(*copy, pdu);
        }
        else
        {
            nas::EncodeNasMessage(msg, pdu);
        }
    }

    // set security header type
    if (state_learner->enableFuzzing)
    {
        int sht = nas::gen_sht();
        pdu.data()[1] = pdu.data()[1] >> 4 << 4 | sht;
        state_learner->response->sht = sht;
        printf("sht: %d\n", pdu.data()[1]);
        printf("secmod: %d\n", FLAG_SECMOD);
        printf("sendNasMessage: %s\n", pdu.toHexString().c_str());
    }
    else if (state_learner->testMessage)
    {
        int sht = state_learner->control_sht;
        pdu.data()[1] = pdu.data()[1] >> 4 << 4 | sht;
        printf("sht: %d\n", pdu.data()[1]);
        printf("secmod: %d\n", FLAG_SECMOD);
        printf("sendNasMessage: %s\n", pdu.toHexString().c_str());
    }

    auto m = std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::UPLINK_NAS_DELIVERY);
    m->pduId = 0;
    m->nasPdu = std::move(pdu);
    m_base->rrcTask->push(std::move(m));

    return EProcRc::OK;
}

// new send function to avoid message copying, assume input is a plaintext encoded message
EProcRc NasMm::sendNasMessageFromPDU(OctetString &&pduIn)
{
    // infer message type from pdu
    nas::EMessageType msgType = (nas::EMessageType) pduIn.data()[2];
    bool hasNsCtx =
        m_usim->m_currentNsCtx && (m_usim->m_currentNsCtx->integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0 ||
                                   m_usim->m_currentNsCtx->ciphering != nas::ETypeOfCipheringAlgorithm::EA0);

    OctetString pdu;
    if (hasNsCtx)
    {
        if (FLAG_SECMOD == 1) // send no security message
        {
            pdu = pduIn.copy();
        }
        else if (FLAG_SECMOD == 2) // send integrity only message
        {
            auto encrypted_copy = nas_enc::EncryptMMPDU(*m_usim->m_currentNsCtx, msgType, std::move(pduIn), true, true);
            nas::EncodeNasMessage(*encrypted_copy, pdu);
        }
        else // default or FLAG_SECMOD == 3, send integrity and ciphering message
        {
            auto encrypted = nas_enc::EncryptMMPDU(*m_usim->m_currentNsCtx, msgType, std::move(pduIn), false, false);
            nas::EncodeNasMessage(*encrypted, pdu);
        }
    }
    else
    {
        if (FLAG_SECMOD > 1)
        {
            state_learner->notify_response("null_action");
            return EProcRc::STAY;
        }
        pdu = pduIn.copy();
    }

    // set security header type
    if (state_learner->enableFuzzing)
    {
        int sht = nas::gen_sht();
        pdu.data()[1] = pdu.data()[1] >> 4 << 4 | sht;
        state_learner->response->sht = sht;
        printf("sht: %d\n", pdu.data()[1]);
        printf("secmod: %d\n", FLAG_SECMOD);
        printf("sendNasMessage: %s\n", pdu.toHexString().c_str());
    }
    else if (state_learner->testMessage)
    {
        int sht = state_learner->control_sht;
        pdu.data()[1] = pdu.data()[1] >> 4 << 4 | sht;
        printf("sht: %d\n", pdu.data()[1]);
        printf("secmod: %d\n", FLAG_SECMOD);
        printf("sendNasMessage: %s\n", pdu.toHexString().c_str());
    }

    auto m = std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::UPLINK_NAS_DELIVERY);
    m->pduId = 0;
    m->nasPdu = std::move(pdu);
    m_base->rrcTask->push(std::move(m));

    return EProcRc::OK;
}

void NasMm::receiveNasMessage(const nas::NasMessage &msg)
{
    FLAG_RECV = 0; // init flag

    if (msg.epd == nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        m_logger->warn("Bad constructed message received (SM)");
        sendMmStatus(nas::EMmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    auto &mmMsg = (const nas::MmMessage &)msg;

    if (mmMsg.sht == nas::ESecurityHeaderType::NOT_PROTECTED)
    {
        // If any NAS signalling message is received as not integrity protected even though the secure exchange of NAS
        // messages has been established by the network, then the NAS shall discard this message
        if (m_usim->m_currentNsCtx && !IsAcceptedWithoutIntegrity((const nas::PlainMmMessage &)mmMsg))
        {
            m_logger->err(
                "Not integrity protected NAS message received after security establishment. Ignoring received "
                "NAS message");
            FLAG_RECV = 1;
            // return;
        }

        receiveMmMessage((const nas::PlainMmMessage &)mmMsg);
        return;
    }

    auto &securedMm = (const nas::SecuredMmMessage &)mmMsg;

    if (mmMsg.sht == nas::ESecurityHeaderType::INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT)
    {
        auto smcMsg = nas::DecodeNasMessage(OctetView{securedMm.plainNasMessage});

        if (smcMsg->epd != nas::EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES ||
            (((const nas::MmMessage &)(*smcMsg)).sht != nas::ESecurityHeaderType::NOT_PROTECTED) ||
            (((const nas::PlainMmMessage &)(*smcMsg)).messageType != nas::EMessageType::SECURITY_MODE_COMMAND))
        {
            m_logger->warn("A valid Security Mode Command expected for given SHT. Ignoring received NAS message");
            sendMmStatus(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
            return;
        }

        ((nas::SecurityModeCommand &)(*smcMsg))._macForNewSC = securedMm.messageAuthenticationCode;
        ((nas::SecurityModeCommand &)(*smcMsg))._originalPlainNasPdu = securedMm.plainNasMessage.copy();

        receiveMmMessage((const nas::PlainMmMessage &)(*smcMsg));
        return;
    }

    if (mmMsg.sht == nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT)
    {
        m_logger->warn("Bad constructed message received (SHT)");
        sendMmStatus(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
        return;
    }

    if (!m_usim->m_currentNsCtx)
    {
        m_logger->err("Secured NAS message received while no security context");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    if (m_usim->m_currentNsCtx->integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0)
    {
        if (!checkForReplay(securedMm))
        {
            m_logger->warn("Replayed NAS message detected, discarding the message");
            FLAG_RECV = 2;
            // return;
        }
    }

    auto decrypted = nas_enc::Decrypt(*m_usim->m_currentNsCtx, securedMm);
    if (decrypted == nullptr)
    {
        m_logger->err("MAC mismatch in NAS encryption. Ignoring received NAS Message.");
        sendMmStatus(nas::EMmCause::MAC_FAILURE);
        return;
    }

    auto &innerMsg = *decrypted;
    if (innerMsg.epd == nas::EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES)
    {
        auto &innerMm = (const nas::MmMessage &)innerMsg;
        if (innerMm.sht == nas::ESecurityHeaderType::NOT_PROTECTED)
            receiveMmMessage((const nas::PlainMmMessage &)innerMm);
        else
        {
            m_logger->warn("Nested protected NAS messages detected");
            receiveNasMessage(innerMsg);
        }
    }
    else if (innerMsg.epd == nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        m_sm->receiveSmMessage((const nas::SmMessage &)(innerMsg));
    }
    else
    {
        m_logger->warn("Bad constructed message received (EPD)");
        sendMmStatus(nas::EMmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
    }
}

// state learner: receiveMmMessage
void NasMm::receiveMmMessage(const nas::PlainMmMessage &msg)
{
    std::string msg_name;
    switch (msg.messageType)
    {
    case nas::EMessageType::REGISTRATION_ACCEPT:
        receiveRegistrationAccept((const nas::RegistrationAccept &)msg);
        m_logger->warn("receiveRegistrationAccept\n");
        msg_name = "registrationAccept";
        break;
    case nas::EMessageType::REGISTRATION_REJECT:
        receiveRegistrationReject((const nas::RegistrationReject &)msg);
        m_logger->warn("receiveRegistrationReject\n");
        msg_name = "registrationReject";
        state_learner->is_ignored = true;
        break;
    case nas::EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING:
        receiveDeregistrationAccept((const nas::DeRegistrationAcceptUeOriginating &)msg);
        m_logger->warn("receiveDeregistrationAccept\n");
        msg_name = "deregistrationAccept";
        state_learner->is_ignored = true;
        break;
    case nas::EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED:
        receiveDeregistrationRequest((const nas::DeRegistrationRequestUeTerminated &)msg);
        m_logger->warn("receiveDeregistrationRequest\n");
        msg_name = "deregistrationRequest";
        break;
    case nas::EMessageType::SERVICE_REJECT:
        receiveServiceReject((const nas::ServiceReject &)msg);
        m_logger->warn("receiveServiceReject\n");
        msg_name = "serviceReject";
        state_learner->is_ignored = true;
        break;
    case nas::EMessageType::SERVICE_ACCEPT:
        receiveServiceAccept((const nas::ServiceAccept &)msg);
        m_logger->warn("receiveServiceAccept\n");
        msg_name = "serviceAccept";
        state_learner->is_ignored = true;
        break;
    case nas::EMessageType::CONFIGURATION_UPDATE_COMMAND:
        receiveConfigurationUpdate((const nas::ConfigurationUpdateCommand &)msg);
        m_logger->warn("receiveConfigurationUpdate\n");
        msg_name = "configurationUpdateCommand";
        break;
    case nas::EMessageType::AUTHENTICATION_REQUEST:
        receiveAuthenticationRequest((const nas::AuthenticationRequest &)msg);
        m_logger->warn("receiveAuthenticationRequest\n");
        msg_name = "authenticationRequest";
        break;
    case nas::EMessageType::AUTHENTICATION_REJECT:
        receiveAuthenticationReject((const nas::AuthenticationReject &)msg);
        m_logger->warn("receiveAuthenticationReject\n");
        msg_name = "authenticationReject";
        break;
    case nas::EMessageType::AUTHENTICATION_RESULT:
        receiveAuthenticationResult((const nas::AuthenticationResult &)msg);
        m_logger->warn("receiveAuthenticationResult\n");
        msg_name = "authenticationResult";
        break;
    case nas::EMessageType::IDENTITY_REQUEST:
        receiveIdentityRequest((const nas::IdentityRequest &)msg);
        m_logger->warn("receiveIdentityRequest\n");
        msg_name = "identityRequest";
        break;
    case nas::EMessageType::SECURITY_MODE_COMMAND:
        receiveSecurityModeCommand((const nas::SecurityModeCommand &)msg);
        m_logger->warn("receiveSecurityModeCommand\n");
        msg_name = "securityModeCommand";
        break;
    case nas::EMessageType::FIVEG_MM_STATUS:
        receiveMmStatus((const nas::FiveGMmStatus &)msg);
        m_logger->warn("receiveMmStatus\n");
        msg_name = "gmmStatus";
        state_learner->is_ignored = true;
        break;
    case nas::EMessageType::DL_NAS_TRANSPORT:
        receiveDlNasTransport((const nas::DlNasTransport &)msg);
        m_logger->warn("receiveDlNasTransport\n");
        msg_name = "dlNasTransport";
        break;
    default:
        m_logger->err("Unhandled NAS MM message received [%d]", (int)msg.messageType);
        // state_learner->notify_response("Unhandled NAS MM message received");
        break;
    }

    if (FLAG_RECV == 1)
    {
        // msg_name.append("_nosec");
    }
    else if (FLAG_RECV == 2)
    {
        // msg_name.append("_replay");
        return;
    }
    if (!state_learner->enableFuzzing)
    {
        if (msg_name != "dlNasTransport")
        {
            state_learner->notify_response(msg_name);
        }
    }
    else
    {
        if (state_learner->response != nullptr)
        {
            if (msg_name != "gmmStatus" && msg_name != "dlNasTransport")
            {
                state_learner->response->ret_type = msg_name;
                OctetString pdu;
                nas::EncodeNasMessage(msg, pdu);
                state_learner->response->ret_msg = pdu.toHexString();
                printf("receiveMmMessage: %s\n", state_learner->response->ret_msg.c_str());
            }
        }
        else
        {
            throw std::runtime_error("Response struct is null");
        }
    }
}

void NasMm::sendMmStatus(nas::EMmCause cause)
{
    m_logger->warn("Sending MM Status with cause [%s]", nas::utils::EnumToString(cause));

    nas::FiveGMmStatus m;
    m.mmCause.value = cause;
    // StateLearner: don't send the message
    // sendNasMessage(m);
}

void NasMm::receiveMmStatus(const nas::FiveGMmStatus &msg)
{
    m_logger->err("MM status received with cause [%s]", nas::utils::EnumToString(msg.mmCause.value));
    if (state_learner->response != nullptr){
        state_learner->response->mm_status = nas::utils::EnumToString(msg.mmCause.value);
    }
}

bool NasMm::checkForReplay(const nas::SecuredMmMessage &msg)
{
    int n = static_cast<int>(msg.sequenceNumber);

    if (m_usim->m_currentNsCtx)
    {
        auto &lastNasSequenceNums = m_usim->m_currentNsCtx->lastNasSequenceNums;

        for (int seq : lastNasSequenceNums)
            if (seq == n)
                return false;

        lastNasSequenceNums.push_back(n);
        while (lastNasSequenceNums.size() > 16)
            lastNasSequenceNums.pop_front();
    }

    return true;
}

} // namespace nr::ue

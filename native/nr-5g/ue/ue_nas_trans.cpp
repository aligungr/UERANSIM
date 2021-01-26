//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_enc.hpp"
#include "ue_nas_task.hpp"

#include <nas_utils.hpp>

namespace nr::ue
{

void NasTask::receiveNasMessage(const nas::NasMessage &msg)
{
    if (msg.epd == nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        logger->warn("Bad constructed message received (SM)");
        sendMmStatus(nas::EMmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    auto &mm = (const nas::MmMessage &)msg;

    if (mm.sht == nas::ESecurityHeaderType::NOT_PROTECTED)
    {
        receiveMmMessage((const nas::PlainMmMessage &)mm);
        return;
    }

    auto &securedMm = (const nas::SecuredMmMessage &)mm;

    if (mm.sht == nas::ESecurityHeaderType::INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT)
    {
        auto smcMsg = nas::DecodeNasMessage(OctetBuffer{securedMm.plainNasMessage});

        if (smcMsg->epd != nas::EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES ||
            (((const nas::MmMessage &)(*smcMsg)).sht != nas::ESecurityHeaderType::NOT_PROTECTED) ||
            (((const nas::PlainMmMessage &)(*smcMsg)).messageType != nas::EMessageType::SECURITY_MODE_COMMAND))
        {
            logger->warn("A valid Security Mode Command expected for given SHT. Ignoring received NAS message");
            sendMmStatus(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
            return;
        }

        receiveMmMessage((const nas::PlainMmMessage &)(*smcMsg));
        return;
    }

    if (mm.sht == nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT)
    {
        logger->warn("Bad constructed message received (SHT)");
        sendMmStatus(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
        return;
    }

    if (!currentNsCtx.has_value())
    {
        logger->warn("Secured NAS message received while no security context");
        sendMmStatus(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    auto decrypted = nas_enc::Decrypt(*currentNsCtx, securedMm);
    if (decrypted == nullptr)
    {
        logger->err("MAC mismatch in NAS encryption. Ignoring received NAS Message.");
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
            logger->warn("Nested protected NAS messages detected");
            receiveNasMessage(innerMsg);
        }
    }
    else if (innerMsg.epd == nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
        receiveSmMessage((const nas::SmMessage &)(innerMsg));
    else
    {
        logger->warn("Bad constructed message received (EPD)");
        sendMmStatus(nas::EMmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED);
    }
}

void NasTask::receiveMmMessage(const nas::PlainMmMessage &msg)
{
    // TODO: trigger on receive

    switch (msg.messageType)
    {
    case nas::EMessageType::REGISTRATION_ACCEPT:
        receiveRegistrationAccept((const nas::RegistrationAccept &)msg);
        break;
    case nas::EMessageType::REGISTRATION_REJECT:
        receiveRegistrationReject((const nas::RegistrationReject &)msg);
        break;
    case nas::EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING:
        receiveDeregistrationAccept((const nas::DeRegistrationAcceptUeOriginating &)msg);
        break;
    case nas::EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED:
        receiveDeregistrationRequest((const nas::DeRegistrationRequestUeTerminated &)msg);
        break;
    case nas::EMessageType::SERVICE_REJECT:
        receiveServiceReject((const nas::ServiceReject &)msg);
        break;
    case nas::EMessageType::SERVICE_ACCEPT:
        receiveServiceAccept((const nas::ServiceAccept &)msg);
        break;
    case nas::EMessageType::CONFIGURATION_UPDATE_COMMAND:
        receiveConfigurationUpdate((const nas::ConfigurationUpdateCommand &)msg);
        break;
    case nas::EMessageType::AUTHENTICATION_REQUEST:
        receiveAuthenticationRequest((const nas::AuthenticationRequest &)msg);
        break;
    case nas::EMessageType::AUTHENTICATION_RESPONSE:
        receiveAuthenticationResponse((const nas::AuthenticationResponse &)msg);
        break;
    case nas::EMessageType::AUTHENTICATION_REJECT:
        receiveAuthenticationReject((const nas::AuthenticationReject &)msg);
        break;
    case nas::EMessageType::AUTHENTICATION_RESULT:
        receiveAuthenticationResult((const nas::AuthenticationResult &)msg);
        break;
    case nas::EMessageType::IDENTITY_REQUEST:
        receiveIdentityRequest((const nas::IdentityRequest &)msg);
        break;
    case nas::EMessageType::SECURITY_MODE_COMMAND:
        receiveSecurityModeCommand((const nas::SecurityModeCommand &)msg);
        break;
    case nas::EMessageType::FIVEG_MM_STATUS:
        receiveMmStatus((const nas::FiveGMmStatus &)msg);
        break;
    case nas::EMessageType::DL_NAS_TRANSPORT:
        receiveDlNasTransport((const nas::DlNasTransport &)msg);
        break;
    default:
        logger->err("Unhandled NAS MM message received: %d", (int)msg.messageType);
        break;
    }
}

void NasTask::receiveDlNasTransport(const nas::DlNasTransport &msg)
{
    if (msg.payloadContainerType.payloadContainerType != nas::EPayloadContainerType::N1_SM_INFORMATION)
    {
        logger->err("Unhandled DL NAS Transport type: %d", (int)msg.payloadContainerType.payloadContainerType);
        return;
    }

    OctetBuffer buff{msg.payloadContainer.data.data(), static_cast<size_t>(msg.payloadContainer.data.length())};
    auto sm = nas::DecodeNasMessage(buff);
    if (sm->epd != nas::EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES)
    {
        logger->err("Bad payload container in DL NAS Transport");
        return;
    }

    receiveSmMessage((const nas::SmMessage &)(*sm));
}

void NasTask::receiveSmMessage(const nas::SmMessage &msg)
{
    // TODO: trigger on receive

    switch (msg.messageType)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        receivePduSessionEstablishmentAccept((const nas::PduSessionEstablishmentAccept &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        receivePduSessionEstablishmentReject((const nas::PduSessionEstablishmentReject &)msg);
        break;
    case nas::EMessageType::FIVEG_SM_STATUS:
        receiveSmStatus((const nas::FiveGSmStatus &)msg);
        break;
    default:
        logger->err("Unhandled NAS SM message received: %d", (int)msg.messageType);
        break;
    }
}

void NasTask::sendNasMessage(const nas::PlainMmMessage &msg)
{
    // TODO trigger on send

    OctetString pdu{};

    if (currentNsCtx.has_value() && (currentNsCtx->integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0 ||
                                     currentNsCtx->ciphering != nas::ETypeOfCipheringAlgorithm::EA0))
    {
        auto secured = nas_enc::Encrypt(*currentNsCtx, msg);
        nas::EncodeNasMessage(*secured, pdu);
    }
    else
    {
        nas::EncodeNasMessage(msg, pdu);
    }

    base->rrcTask->push(new NwUplinkNasDelivery(std::move(pdu)));
}

void NasTask::sendMmStatus(nas::EMmCause cause)
{
    logger->warn("Sending MM Status with cause %s", nas::utils::EnumToString(cause));

    nas::FiveGMmStatus m;
    m.mmCause.value = cause;
    sendNasMessage(m);
}

} // namespace nr::ue
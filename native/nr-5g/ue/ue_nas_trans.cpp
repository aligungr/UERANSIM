//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"
#include <nas_utils.hpp>

namespace nr::ue
{

void NasTask::receiveNasMessage(const nas::NasMessage &msg)
{
    // todo
}

void NasTask::sendNasMessage(const nas::NasMessage &msg)
{
    // TODO
}

void NasTask::receiveMmMessage(const nas::PlainMmMessage &msg)
{
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
        receiveMmCause((const nas::IE5gMmCause &)msg);
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

    OctetBuffer buff{const_cast<uint8_t *>(msg.payloadContainer.data.data()),
                     static_cast<size_t>(msg.payloadContainer.data.length())};
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
    switch (msg.messageType)
    {
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        receivePduSessionEstablishmentAccept((const nas::PduSessionEstablishmentAccept &)msg);
        break;
    case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        receivePduSessionEstablishmentReject((const nas::PduSessionEstablishmentReject &)msg);
        break;
    default:
        logger->err("Unhandled NAS SM message received: %d", (int)msg.messageType);
        break;
    }
}

void NasTask::sendMmMessage(const nas::PlainMmMessage &msg)
{
    sendNasMessage(msg);
}

void NasTask::sendSmMessage(int psi, const nas::SmMessage &msg)
{
    nas::UlNasTransport m;
    m.payloadContainerType.payloadContainerType = nas::EPayloadContainerType::N1_SM_INFORMATION;
    m.pduSessionId = nas::IEPduSessionIdentity2{};
    m.pduSessionId->value = psi;
    m.requestType = nas::IERequestType{};
    m.requestType->requestType = nas::ERequestType::INITIAL_REQUEST; // TODO
    if (!base->config->nssais.empty())
        m.sNssa = nas::utils::SNssaiFrom(base->config->nssais[0]); // TODO S-NNSAI per session
    m.dnn = nas::IEDnn{};                                          // TODO: DNN per session
    m.dnn->apn = OctetString::FromAscii(base->config->dnn);        // TODO check if it is ASCII or UTF-8

    sendMmMessage(m);
}

} // namespace nr::ue
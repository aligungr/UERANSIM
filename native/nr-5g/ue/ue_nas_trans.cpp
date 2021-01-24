//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"

namespace nr::ue
{

void NasTask::receiveNasMessage(const nas::NasMessage &msg)
{
    // todo
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
        // TODO
        break;
    default:
        logger->err("Unhandled NAS MM message received: %d", (int)msg.messageType);
        break;
    }
}

void NasTask::sendMmMessage(const nas::PlainMmMessage &msg)
{
    // TODO
}

} // namespace nr::ue
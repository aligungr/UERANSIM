//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "encode.hpp"

#include <stdexcept>

// fuzzing
#include <ue/app/state_learner.hpp>

namespace nas
{

template <typename T>
static void EncodeViaBuilder(T &msg, OctetString &stream)
{
    NasMessageBuilder builder{};
    msg.onBuild(builder);

    for (auto &f : builder.mandatoryEncoders)
        f(stream);
    for (auto &f : builder.optionalEncoders)
        f(stream);
}

// fuzzing
template <typename T>
static void MutateViaMutator(T &msg)
{
    NasMessageMutator mutator{};
    msg.onMutate(mutator);
}

static void EncodeMm(PlainMmMessage &msg, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(msg.messageType));

    switch (msg.messageType)
    {
    case EMessageType::REGISTRATION_REQUEST:
        EncodeViaBuilder((RegistrationRequest &)msg, stream);
        break;
    case EMessageType::REGISTRATION_ACCEPT:
        EncodeViaBuilder((RegistrationAccept &)msg, stream);
        break;
    case EMessageType::REGISTRATION_COMPLETE:
        EncodeViaBuilder((RegistrationComplete &)msg, stream);
        break;
    case EMessageType::REGISTRATION_REJECT:
        EncodeViaBuilder((RegistrationReject &)msg, stream);
        break;
    case EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING:
        EncodeViaBuilder((DeRegistrationRequestUeOriginating &)msg, stream);
        break;
    case EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING:
        EncodeViaBuilder((DeRegistrationAcceptUeOriginating &)msg, stream);
        break;
    case EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED:
        EncodeViaBuilder((DeRegistrationRequestUeTerminated &)msg, stream);
        break;
    case EMessageType::DEREGISTRATION_ACCEPT_UE_TERMINATED:
        EncodeViaBuilder((DeRegistrationAcceptUeTerminated &)msg, stream);
        break;
    case EMessageType::SERVICE_REQUEST:
        EncodeViaBuilder((ServiceRequest &)msg, stream);
        break;
    case EMessageType::SERVICE_REJECT:
        EncodeViaBuilder((ServiceReject &)msg, stream);
        break;
    case EMessageType::SERVICE_ACCEPT:
        EncodeViaBuilder((ServiceAccept &)msg, stream);
        break;
    case EMessageType::CONFIGURATION_UPDATE_COMMAND:
        EncodeViaBuilder((ConfigurationUpdateCommand &)msg, stream);
        break;
    case EMessageType::CONFIGURATION_UPDATE_COMPLETE:
        EncodeViaBuilder((ConfigurationUpdateComplete &)msg, stream);
        break;
    case EMessageType::AUTHENTICATION_REQUEST:
        EncodeViaBuilder((AuthenticationRequest &)msg, stream);
        break;
    case EMessageType::AUTHENTICATION_RESPONSE:
        EncodeViaBuilder((AuthenticationResponse &)msg, stream);
        break;
    case EMessageType::AUTHENTICATION_REJECT:
        EncodeViaBuilder((AuthenticationReject &)msg, stream);
        break;
    case EMessageType::AUTHENTICATION_FAILURE:
        EncodeViaBuilder((AuthenticationFailure &)msg, stream);
        break;
    case EMessageType::AUTHENTICATION_RESULT:
        EncodeViaBuilder((AuthenticationResult &)msg, stream);
        break;
    case EMessageType::IDENTITY_REQUEST:
        EncodeViaBuilder((IdentityRequest &)msg, stream);
        break;
    case EMessageType::IDENTITY_RESPONSE:
        EncodeViaBuilder((IdentityResponse &)msg, stream);
        break;
    case EMessageType::SECURITY_MODE_COMMAND:
        EncodeViaBuilder((SecurityModeCommand &)msg, stream);
        break;
    case EMessageType::SECURITY_MODE_COMPLETE:
        EncodeViaBuilder((SecurityModeComplete &)msg, stream);
        break;
    case EMessageType::SECURITY_MODE_REJECT:
        EncodeViaBuilder((SecurityModeReject &)msg, stream);
        break;
    case EMessageType::FIVEG_MM_STATUS:
        EncodeViaBuilder((FiveGMmStatus &)msg, stream);
        break;
    case EMessageType::NOTIFICATION:
        EncodeViaBuilder((Notification &)msg, stream);
        break;
    case EMessageType::NOTIFICATION_RESPONSE:
        EncodeViaBuilder((NotificationResponse &)msg, stream);
        break;
    case EMessageType::UL_NAS_TRANSPORT:
        EncodeViaBuilder((UlNasTransport &)msg, stream);
        break;
    case EMessageType::DL_NAS_TRANSPORT:
        EncodeViaBuilder((DlNasTransport &)msg, stream);
        break;
    default:
        // printf("invalid NAS message type: %d\n", msg.messageType);
        throw std::runtime_error("invalid NAS message type");
    }
}

// need to define before MutateMm()
static void MutateSm(SmMessage &msg)
{
    // 1/5 chance to mutate pti or pduSessionId
    if (generate_int(5) == 0)
    {
        msg.pti = generate_bit(32);
    }
    if (generate_int(5) == 0)
    {
        msg.pduSessionId = generate_bit(32);
    }
    
    switch (msg.messageType)
    {
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
        MutateViaMutator((PduSessionEstablishmentRequest &)msg);
        break;
    case EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        MutateViaMutator((PduSessionEstablishmentAccept &)msg);
        break;
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        MutateViaMutator((PduSessionEstablishmentReject &)msg);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMMAND:
        MutateViaMutator((PduSessionAuthenticationCommand &)msg);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
        MutateViaMutator((PduSessionAuthenticationComplete &)msg);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_RESULT:
        MutateViaMutator((PduSessionAuthenticationResult &)msg);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
        MutateViaMutator((PduSessionModificationRequest &)msg);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_REJECT:
        MutateViaMutator((PduSessionModificationReject &)msg);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND:
        MutateViaMutator((PduSessionModificationCommand &)msg);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
        MutateViaMutator((PduSessionModificationComplete &)msg);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
        MutateViaMutator((PduSessionModificationCommandReject &)msg);
        break;
    case EMessageType::PDU_SESSION_RELEASE_REQUEST:
        MutateViaMutator((PduSessionReleaseRequest &)msg);
        break;
    case EMessageType::PDU_SESSION_RELEASE_REJECT:
        MutateViaMutator((PduSessionReleaseReject &)msg);
        break;
    case EMessageType::PDU_SESSION_RELEASE_COMMAND:
        MutateViaMutator((PduSessionReleaseCommand &)msg);
        break;
    case EMessageType::PDU_SESSION_RELEASE_COMPLETE:
        MutateViaMutator((PduSessionReleaseComplete &)msg);
        break;
    case EMessageType::FIVEG_SM_STATUS:
        MutateViaMutator((FiveGSmStatus &)msg);
        break;
    default:
        throw std::runtime_error("invalid NAS message type");
    }
}

// fuzzing
static void MutateMm(PlainMmMessage &msg)
{
    switch (msg.messageType)
    {
    case EMessageType::REGISTRATION_REQUEST:
        MutateViaMutator((RegistrationRequest &)msg);
        break;
    case EMessageType::REGISTRATION_ACCEPT:
        MutateViaMutator((RegistrationAccept &)msg);
        break;
    case EMessageType::REGISTRATION_COMPLETE:
        MutateViaMutator((RegistrationComplete &)msg);
        break;
    case EMessageType::REGISTRATION_REJECT:
        MutateViaMutator((RegistrationReject &)msg);
        break;
    case EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING:
        MutateViaMutator((DeRegistrationRequestUeOriginating &)msg);
        break;
    case EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING:
        MutateViaMutator((DeRegistrationAcceptUeOriginating &)msg);
        break;
    case EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED:
        MutateViaMutator((DeRegistrationRequestUeTerminated &)msg);
        break;
    case EMessageType::DEREGISTRATION_ACCEPT_UE_TERMINATED:
        MutateViaMutator((DeRegistrationAcceptUeTerminated &)msg);
        break;
    case EMessageType::SERVICE_REQUEST:
        MutateViaMutator((ServiceRequest &)msg);
        break;
    case EMessageType::SERVICE_REJECT:
        MutateViaMutator((ServiceReject &)msg);
        break;
    case EMessageType::SERVICE_ACCEPT:
        MutateViaMutator((ServiceAccept &)msg);
        break;
    case EMessageType::CONFIGURATION_UPDATE_COMMAND:
        MutateViaMutator((ConfigurationUpdateCommand &)msg);
        break;
    case EMessageType::CONFIGURATION_UPDATE_COMPLETE:
        MutateViaMutator((ConfigurationUpdateComplete &)msg);
        break;
    case EMessageType::AUTHENTICATION_REQUEST:
        MutateViaMutator((AuthenticationRequest &)msg);
        break;
    case EMessageType::AUTHENTICATION_RESPONSE:
        MutateViaMutator((AuthenticationResponse &)msg);
        break;
    case EMessageType::AUTHENTICATION_REJECT:
        MutateViaMutator((AuthenticationReject &)msg);
        break;
    case EMessageType::AUTHENTICATION_FAILURE:
        MutateViaMutator((AuthenticationFailure &)msg);
        break;
    case EMessageType::AUTHENTICATION_RESULT:
        MutateViaMutator((AuthenticationResult &)msg);
        break;
    case EMessageType::IDENTITY_REQUEST:
        MutateViaMutator((IdentityRequest &)msg);
        break;
    case EMessageType::IDENTITY_RESPONSE:
        MutateViaMutator((IdentityResponse &)msg);
        break;
    case EMessageType::SECURITY_MODE_COMMAND:
        MutateViaMutator((SecurityModeCommand &)msg);
        break;
    case EMessageType::SECURITY_MODE_COMPLETE:
        MutateViaMutator((SecurityModeComplete &)msg);
        break;
    case EMessageType::SECURITY_MODE_REJECT:
        MutateViaMutator((SecurityModeReject &)msg);
        break;
    case EMessageType::FIVEG_MM_STATUS:
        MutateViaMutator((FiveGMmStatus &)msg);
        break;
    case EMessageType::NOTIFICATION:
        MutateViaMutator((Notification &)msg);
        break;
    case EMessageType::NOTIFICATION_RESPONSE:
        MutateViaMutator((NotificationResponse &)msg);
        break;
    case EMessageType::UL_NAS_TRANSPORT:{
        UlNasTransport &ul = (UlNasTransport &)msg;
        if (ul.payloadContainerType.payloadContainerType == EPayloadContainerType::N1_SM_INFORMATION)
            MutateSm((SmMessage &) *DecodeNasMessage(OctetView{ul.payloadContainer.data}));
        MutateViaMutator(ul);
        break;}
    case EMessageType::DL_NAS_TRANSPORT:
        MutateViaMutator((DlNasTransport &)msg);
        break;
    default:
        throw std::runtime_error("invalid NAS message type");
    }
}

static void EncodeSecured(SecuredMmMessage &msg, OctetString &stream)
{
    stream.appendOctet4(msg.messageAuthenticationCode);
    stream.appendOctet(msg.sequenceNumber);
    stream.append(msg.plainNasMessage);
}

static void EncodeSm(SmMessage &msg, OctetString &stream)
{
    stream.appendOctet(msg.pduSessionId);
    stream.appendOctet(msg.pti);
    stream.appendOctet(static_cast<int>(msg.messageType));

    switch (msg.messageType)
    {
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
        EncodeViaBuilder((PduSessionEstablishmentRequest &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        EncodeViaBuilder((PduSessionEstablishmentAccept &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        EncodeViaBuilder((PduSessionEstablishmentReject &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMMAND:
        EncodeViaBuilder((PduSessionAuthenticationCommand &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
        EncodeViaBuilder((PduSessionAuthenticationComplete &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_AUTHENTICATION_RESULT:
        EncodeViaBuilder((PduSessionAuthenticationResult &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
        EncodeViaBuilder((PduSessionModificationRequest &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_REJECT:
        EncodeViaBuilder((PduSessionModificationReject &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND:
        EncodeViaBuilder((PduSessionModificationCommand &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
        EncodeViaBuilder((PduSessionModificationComplete &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
        EncodeViaBuilder((PduSessionModificationCommandReject &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_RELEASE_REQUEST:
        EncodeViaBuilder((PduSessionReleaseRequest &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_RELEASE_REJECT:
        EncodeViaBuilder((PduSessionReleaseReject &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_RELEASE_COMMAND:
        EncodeViaBuilder((PduSessionReleaseCommand &)msg, stream);
        break;
    case EMessageType::PDU_SESSION_RELEASE_COMPLETE:
        EncodeViaBuilder((PduSessionReleaseComplete &)msg, stream);
        break;
    case EMessageType::FIVEG_SM_STATUS:
        EncodeViaBuilder((FiveGSmStatus &)msg, stream);
        break;
    default:
        throw std::runtime_error("invalid NAS message type");
    }
}

void EncodeNasMessage(const NasMessage &msg, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(msg.epd));
    if (msg.epd == EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES)
    {
        auto &mm = (MmMessage &)msg;
        stream.appendOctet(static_cast<int>(mm.sht));

        if (mm.sht == ESecurityHeaderType::NOT_PROTECTED)
            EncodeMm((PlainMmMessage &)mm, stream);
        else
            EncodeSecured((SecuredMmMessage &)mm, stream);
    }
    else
        EncodeSm((SmMessage &)msg, stream);
}

void MutateNasMessage(const NasMessage &msg)
{
    if (msg.epd == EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES)
    {
        auto &mm = (MmMessage &)msg;
        
        MutateMm((PlainMmMessage &)mm);
    }
    else
        MutateSm((SmMessage &)msg);
}

static SecuredMmMessage *DecodeSecuredMmMessage(const OctetView &stream, ESecurityHeaderType sht)
{
    auto *p = new SecuredMmMessage();
    p->messageAuthenticationCode = stream.read4();
    p->sequenceNumber = stream.read();
    p->plainNasMessage = stream.readOctetString();
    return p;
}

template <typename T>
static T *DecodeViaBuilder(const OctetView &stream)
{
    T *p = new T();

    NasMessageBuilder builder{};
    p->onBuild(builder);

    for (auto &f : builder.mandatoryDecoders)
        f(stream);

    while (stream.hasNext())
    {
        int iei = stream.peekI();
        if (builder.optionalDecoders.count(iei))
            builder.optionalDecoders[iei](stream);
        else if (builder.optionalDecoders.count((iei >> 4) & 0xF))
            builder.optionalDecoders[(iei >> 4) & 0xF](stream);
        // fuzzing: notify error to stop mutation on certain message
        else
        {
            if (nr::ue::state_learner->dec_before_mut) {
                nr::ue::state_learner->notify_response("decode error");
                std::this_thread::sleep_for(std::chrono::milliseconds(100)); // sleep before crash to make sure the message is received
            }
            throw std::runtime_error("Bad constructed NAS message");
        }
    }

    return p;
}

static PlainMmMessage *DecodePlainMmMessage(const OctetView &stream, EMessageType messageType)
{
    switch (messageType)
    {
    case EMessageType::REGISTRATION_REQUEST:
        return DecodeViaBuilder<RegistrationRequest>(stream);
    case EMessageType::REGISTRATION_ACCEPT:
        return DecodeViaBuilder<RegistrationAccept>(stream);
    case EMessageType::REGISTRATION_COMPLETE:
        return DecodeViaBuilder<RegistrationComplete>(stream);
    case EMessageType::REGISTRATION_REJECT:
        return DecodeViaBuilder<RegistrationReject>(stream);
    case EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING:
        return DecodeViaBuilder<DeRegistrationRequestUeOriginating>(stream);
    case EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING:
        return DecodeViaBuilder<DeRegistrationAcceptUeOriginating>(stream);
    case EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED:
        return DecodeViaBuilder<DeRegistrationRequestUeTerminated>(stream);
    case EMessageType::DEREGISTRATION_ACCEPT_UE_TERMINATED:
        return DecodeViaBuilder<DeRegistrationAcceptUeTerminated>(stream);
    case EMessageType::SERVICE_REQUEST:
        return DecodeViaBuilder<ServiceRequest>(stream);
    case EMessageType::SERVICE_REJECT:
        return DecodeViaBuilder<ServiceReject>(stream);
    case EMessageType::SERVICE_ACCEPT:
        return DecodeViaBuilder<ServiceAccept>(stream);
    case EMessageType::CONFIGURATION_UPDATE_COMMAND:
        return DecodeViaBuilder<ConfigurationUpdateCommand>(stream);
    case EMessageType::CONFIGURATION_UPDATE_COMPLETE:
        return DecodeViaBuilder<ConfigurationUpdateComplete>(stream);
    case EMessageType::AUTHENTICATION_REQUEST:
        return DecodeViaBuilder<AuthenticationRequest>(stream);
    case EMessageType::AUTHENTICATION_RESPONSE:
        return DecodeViaBuilder<AuthenticationResponse>(stream);
    case EMessageType::AUTHENTICATION_REJECT:
        return DecodeViaBuilder<AuthenticationReject>(stream);
    case EMessageType::AUTHENTICATION_FAILURE:
        return DecodeViaBuilder<AuthenticationFailure>(stream);
    case EMessageType::AUTHENTICATION_RESULT:
        return DecodeViaBuilder<AuthenticationResult>(stream);
    case EMessageType::IDENTITY_REQUEST:
        return DecodeViaBuilder<IdentityRequest>(stream);
    case EMessageType::IDENTITY_RESPONSE:
        return DecodeViaBuilder<IdentityResponse>(stream);
    case EMessageType::SECURITY_MODE_COMMAND:
        return DecodeViaBuilder<SecurityModeCommand>(stream);
    case EMessageType::SECURITY_MODE_COMPLETE:
        return DecodeViaBuilder<SecurityModeComplete>(stream);
    case EMessageType::SECURITY_MODE_REJECT:
        return DecodeViaBuilder<SecurityModeReject>(stream);
    case EMessageType::FIVEG_MM_STATUS:
        return DecodeViaBuilder<FiveGMmStatus>(stream);
    case EMessageType::NOTIFICATION:
        return DecodeViaBuilder<Notification>(stream);
    case EMessageType::NOTIFICATION_RESPONSE:
        return DecodeViaBuilder<NotificationResponse>(stream);
    case EMessageType::UL_NAS_TRANSPORT:
        return DecodeViaBuilder<UlNasTransport>(stream);
    case EMessageType::DL_NAS_TRANSPORT:
        return DecodeViaBuilder<DlNasTransport>(stream);
    default:
        throw std::runtime_error("invalid NAS message type");
    }
}

static SmMessage *DecodeSmMessage(const OctetView &stream, EMessageType messageType)
{
    switch (messageType)
    {
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
        return DecodeViaBuilder<PduSessionEstablishmentRequest>(stream);
    case EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT:
        return DecodeViaBuilder<PduSessionEstablishmentAccept>(stream);
    case EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT:
        return DecodeViaBuilder<PduSessionEstablishmentReject>(stream);
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMMAND:
        return DecodeViaBuilder<PduSessionAuthenticationCommand>(stream);
    case EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
        return DecodeViaBuilder<PduSessionAuthenticationComplete>(stream);
    case EMessageType::PDU_SESSION_AUTHENTICATION_RESULT:
        return DecodeViaBuilder<PduSessionAuthenticationResult>(stream);
    case EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
        return DecodeViaBuilder<PduSessionModificationRequest>(stream);
    case EMessageType::PDU_SESSION_MODIFICATION_REJECT:
        return DecodeViaBuilder<PduSessionModificationReject>(stream);
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND:
        return DecodeViaBuilder<PduSessionModificationCommand>(stream);
    case EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
        return DecodeViaBuilder<PduSessionModificationComplete>(stream);
    case EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
        return DecodeViaBuilder<PduSessionModificationCommandReject>(stream);
    case EMessageType::PDU_SESSION_RELEASE_REQUEST:
        return DecodeViaBuilder<PduSessionReleaseRequest>(stream);
    case EMessageType::PDU_SESSION_RELEASE_REJECT:
        return DecodeViaBuilder<PduSessionReleaseReject>(stream);
    case EMessageType::PDU_SESSION_RELEASE_COMMAND:
        return DecodeViaBuilder<PduSessionReleaseCommand>(stream);
    case EMessageType::PDU_SESSION_RELEASE_COMPLETE:
        return DecodeViaBuilder<PduSessionReleaseComplete>(stream);
    case EMessageType::FIVEG_SM_STATUS:
        return DecodeViaBuilder<FiveGSmStatus>(stream);
    default:
        throw std::runtime_error("invalid NAS message type");
    }
}

std::unique_ptr<NasMessage> DecodeNasMessage(const OctetView &stream)
{
    auto epd = static_cast<EExtendedProtocolDiscriminator>(stream.readI());
    if (epd == EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES)
    {
        auto sht = static_cast<ESecurityHeaderType>(stream.readI());
        if (sht == ESecurityHeaderType::NOT_PROTECTED)
        {
            auto messageType = static_cast<EMessageType>(stream.readI());
            PlainMmMessage *p = DecodePlainMmMessage(stream, messageType);
            p->epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
            p->sht = ESecurityHeaderType::NOT_PROTECTED;
            p->messageType = messageType;
            return std::unique_ptr<NasMessage>(p);
        }
        else
        {
            SecuredMmMessage *p = DecodeSecuredMmMessage(stream, sht);
            p->epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
            p->sht = sht;
            return std::unique_ptr<NasMessage>(p);
        }
    }
    else
    {
        auto pduSessionId = stream.readI();
        uint8_t pti = stream.read();
        auto messageType = static_cast<EMessageType>(stream.readI());

        SmMessage *p = DecodeSmMessage(stream, messageType);
        p->epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
        p->pduSessionId = pduSessionId;
        p->pti = pti;
        p->messageType = messageType;

        return std::unique_ptr<NasMessage>(p);
    }
}

} // namespace nas

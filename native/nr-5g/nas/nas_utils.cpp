//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_utils.hpp"

#include <cstring>

namespace nas::utils
{

IESNssai SNssaiFrom(const SliceSupport &v)
{
    IESNssai r;
    r.sst = v.sst;
    if (v.sd.has_value())
        r.sd = v.sd.value();
    return r;
}

IENssai NssaiFrom(const std::vector<SliceSupport> &v)
{
    IENssai r;
    for (auto &x : v)
        r.sNssais.push_back(SNssaiFrom(x));
    return r;
}

bool HasValue(const IEGprsTimer3 &v)
{
    return v.unit != EGprsTimerValueUnit3::DEACTIVATED && v.timerValue != 0;
}

bool HasValue(const IEGprsTimer2 &v)
{
    return v.value != 0;
}

const char *EnumToString(ERegistrationType v)
{
    switch (v)
    {
    case ERegistrationType::INITIAL_REGISTRATION:
        return "Initial Registration";
    case ERegistrationType::MOBILITY_REGISTRATION_UPDATING:
        return "Mobility Registration";
    case ERegistrationType::PERIODIC_REGISTRATION_UPDATING:
        return "Periodic Registration";
    case ERegistrationType::EMERGENCY_REGISTRATION:
        return "Emergency Registration";
    default:
        return "?";
    }
}

const char *EnumToString(EMmCause v)
{
    switch (v)
    {
    case EMmCause::ILLEGAL_UE:
        return "ILLEGAL_UE";
    case EMmCause::PEI_NOT_ACCEPTED:
        return "PEI_NOT_ACCEPTED";
    case EMmCause::ILLEGAL_ME:
        return "ILLEGAL_ME";
    case EMmCause::FIVEG_SERVICES_NOT_ALLOWED:
        return "FIVEG_SERVICES_NOT_ALLOWED";
    case EMmCause::UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK:
        return "UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK";
    case EMmCause::IMPLICITY_DEREGISTERED:
        return "IMPLICITY_DEREGISTERED";
    case EMmCause::PLMN_NOT_ALLOWED:
        return "PLMN_NOT_ALLOWED";
    case EMmCause::TA_NOT_ALLOWED:
        return "TA_NOT_ALLOWED";
    case EMmCause::ROAMING_NOT_ALLOWED_IN_TA:
        return "ROAMING_NOT_ALLOWED_IN_TA";
    case EMmCause::NO_SUITIBLE_CELLS_IN_TA:
        return "NO_SUITIBLE_CELLS_IN_TA";
    case EMmCause::MAC_FAILURE:
        return "MAC_FAILURE";
    case EMmCause::SYNCH_FAILURE:
        return "SYNCH_FAILURE";
    case EMmCause::CONGESTION:
        return "CONGESTION";
    case EMmCause::UE_SECURITY_CAP_MISMATCH:
        return "UE_SECURITY_CAP_MISMATCH";
    case EMmCause::SEC_MODE_REJECTED_UNSPECIFIED:
        return "SEC_MODE_REJECTED_UNSPECIFIED";
    case EMmCause::NON_5G_AUTHENTICATION_UNACCEPTABLE:
        return "NON_5G_AUTHENTICATION_UNACCEPTABLE";
    case EMmCause::N1_MODE_NOT_ALLOWED:
        return "N1_MODE_NOT_ALLOWED";
    case EMmCause::RESTRICTED_NOT_SERVICE_AREA:
        return "RESTRICTED_NOT_SERVICE_AREA";
    case EMmCause::LADN_NOT_AVAILABLE:
        return "LADN_NOT_AVAILABLE";
    case EMmCause::MAX_PDU_SESSIONS_REACHED:
        return "MAX_PDU_SESSIONS_REACHED";
    case EMmCause::INSUFFICIENT_RESOURCES_FOR_SLICE_AND_DNN:
        return "INSUFFICIENT_RESOURCES_FOR_SLICE_AND_DNN";
    case EMmCause::INSUFFICIENT_RESOURCES_FOR_SLICE:
        return "INSUFFICIENT_RESOURCES_FOR_SLICE";
    case EMmCause::NGKSI_ALREADY_IN_USE:
        return "NGKSI_ALREADY_IN_USE";
    case EMmCause::NON_3GPP_ACCESS_TO_CN_NOT_ALLOWED:
        return "NON_3GPP_ACCESS_TO_CN_NOT_ALLOWED";
    case EMmCause::SERVING_NETWORK_NOT_AUTHORIZED:
        return "SERVING_NETWORK_NOT_AUTHORIZED";
    case EMmCause::PAYLOAD_NOT_FORWARDED:
        return "PAYLOAD_NOT_FORWARDED";
    case EMmCause::DNN_NOT_SUPPORTED_OR_NOT_SUBSCRIBED:
        return "DNN_NOT_SUPPORTED_OR_NOT_SUBSCRIBED";
    case EMmCause::INSUFFICIENT_USER_PLANE_RESOURCES:
        return "INSUFFICIENT_USER_PLANE_RESOURCES";
    case EMmCause::SEMANTICALLY_INCORRECT_MESSAGE:
        return "SEMANTICALLY_INCORRECT_MESSAGE";
    case EMmCause::INVALID_MANDATORY_INFORMATION:
        return "INVALID_MANDATORY_INFORMATION";
    case EMmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED:
        return "MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED";
    case EMmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE:
        return "MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE";
    case EMmCause::INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED:
        return "INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED";
    case EMmCause::CONDITIONAL_IE_ERROR:
        return "CONDITIONAL_IE_ERROR";
    case EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE:
        return "MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE";
    case EMmCause::UNSPECIFIED_PROTOCOL_ERROR:
        return "UNSPECIFIED_PROTOCOL_ERROR";
    default:
        return "?";
    }
}

const char *EnumToString(eap::ECode v)
{
    switch (v)
    {
    case eap::ECode::REQUEST:
        return "REQUEST";
    case eap::ECode::RESPONSE:
        return "RESPONSE";
    case eap::ECode::SUCCESS:
        return "SUCCESS";
    case eap::ECode::FAILURE:
        return "FAILURE";
    case eap::ECode::INITIATE:
        return "INITIATE";
    case eap::ECode::FINISH:
        return "FINISH";
    default:
        return "?";
    }
}

const char *EnumToString(ESmCause v)
{
    switch (v)
    {
    case ESmCause::INSUFFICIENT_RESOURCES:
        return "INSUFFICIENT_RESOURCES";
    case ESmCause::MISSING_OR_UNKNOWN_DNN:
        return "MISSING_OR_UNKNOWN_DNN";
    case ESmCause::UNKNOWN_PDU_SESSION_TYPE:
        return "UNKNOWN_PDU_SESSION_TYPE";
    case ESmCause::USER_AUTHENTICATION_OR_AUTHORIZATION_FAILED:
        return "USER_AUTHENTICATION_OR_AUTHORIZATION_FAILED";
    case ESmCause::REQUEST_REJECTED_UNSPECIFIED:
        return "REQUEST_REJECTED_UNSPECIFIED";
    case ESmCause::SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER:
        return "SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER";
    case ESmCause::PTI_ALREADY_IN_USE:
        return "PTI_ALREADY_IN_USE";
    case ESmCause::REGULAR_DEACTIVATION:
        return "REGULAR_DEACTIVATION";
    case ESmCause::REACTIVATION_REQUESTED:
        return "REACTIVATION_REQUESTED";
    case ESmCause::INVALID_PDU_SESSION_IDENTITY:
        return "INVALID_PDU_SESSION_IDENTITY";
    case ESmCause::SEMANTIC_ERRORS_IN_PACKET_FILTERS:
        return "SEMANTIC_ERRORS_IN_PACKET_FILTERS";
    case ESmCause::SYNTACTICAL_ERROR_IN_PACKET_FILTERS:
        return "SYNTACTICAL_ERROR_IN_PACKET_FILTERS";
    case ESmCause::OUT_OF_LADN_SERVICE_AREA:
        return "OUT_OF_LADN_SERVICE_AREA";
    case ESmCause::PTI_MISMATCH:
        return "PTI_MISMATCH";
    case ESmCause::PDU_SESSION_TYPE_IPV4_ONLY_ALLOWED:
        return "PDU_SESSION_TYPE_IPV4_ONLY_ALLOWED";
    case ESmCause::PDU_SESSION_TYPE_IPV6_ONLY_ALLOWED:
        return "PDU_SESSION_TYPE_IPV6_ONLY_ALLOWED";
    case ESmCause::PDU_SESSION_DOES_NOT_EXIST:
        return "PDU_SESSION_DOES_NOT_EXIST";
    case ESmCause::INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE_AND_DNN:
        return "INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE_AND_DNN";
    case ESmCause::NOT_SUPPORTED_SSC_MODE:
        return "NOT_SUPPORTED_SSC_MODE";
    case ESmCause::INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE:
        return "INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE";
    case ESmCause::MISSING_OR_UNKNOWN_DNN_IN_A_SLICE:
        return "MISSING_OR_UNKNOWN_DNN_IN_A_SLICE";
    case ESmCause::INVALID_PTI_VALUE:
        return "INVALID_PTI_VALUE";
    case ESmCause::MAXIMUM_DATA_RATE_PER_UE_FOR_USER_PLANE_INTEGRITY_PROTECTION_IS_TOO_LOW:
        return "MAXIMUM_DATA_RATE_PER_UE_FOR_USER_PLANE_INTEGRITY_PROTECTION_IS_TOO_LOW";
    case ESmCause::SEMANTIC_ERROR_IN_THE_QOS_OPERATION:
        return "SEMANTIC_ERROR_IN_THE_QOS_OPERATION";
    case ESmCause::SYNTACTICAL_ERROR_IN_THE_QOS_OPERATION:
        return "SYNTACTICAL_ERROR_IN_THE_QOS_OPERATION";
    case ESmCause::SEMANTICALLY_INCORRECT_MESSAGE:
        return "SEMANTICALLY_INCORRECT_MESSAGE";
    case ESmCause::INVALID_MANDATORY_INFORMATION:
        return "INVALID_MANDATORY_INFORMATION";
    case ESmCause::MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED:
        return "MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED";
    case ESmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE:
        return "MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE";
    case ESmCause::INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED:
        return "INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED";
    case ESmCause::CONDITIONAL_IE_ERROR:
        return "CONDITIONAL_IE_ERROR";
    case ESmCause::MESSAGE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE:
        return "MESSAGE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE";
    case ESmCause::PROTOCOL_ERROR_UNSPECIFIED:
        return "PROTOCOL_ERROR_UNSPECIFIED";
    default:
        return "?";
    }
}

const char *EnumToString(EPduSessionType v)
{
    switch (v)
    {
    case EPduSessionType::IPV4:
        return "IPV4";
    case EPduSessionType::IPV6:
        return "IPV6";
    case EPduSessionType::IPV4V6:
        return "IPV4V6";
    case EPduSessionType::UNSTRUCTURED:
        return "UNSTRUCTURED";
    case EPduSessionType::ETHERNET:
        return "ETHERNET";
    default:
        return "?";
    }
}

IEDnn DnnFromApn(const std::string &apn)
{
    IEDnn dnn;
    dnn.apn = OctetString::FromSpare(static_cast<int>(apn.length()) + 1);
    dnn.apn.data()[0] = static_cast<uint8_t>(apn.length());
    std::memcpy(dnn.apn.data() + 1, apn.data(), apn.length());
    return dnn;
}

} // namespace nas::utils

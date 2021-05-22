//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "utils.hpp"

#include <algorithm>
#include <cstring>

#include <utils/common.hpp>

namespace nas::utils
{

IESNssai SNssaiFrom(const SingleSlice &v)
{
    IESNssai r;
    r.sst = v.sst;
    if (v.sd.has_value())
        r.sd = v.sd.value();
    return r;
}

IENssai NssaiFrom(const NetworkSlice &v)
{
    IENssai r;
    for (auto &x : v.slices)
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
    case EMmCause::RESTRICTED_SERVICE_AREA:
        return "RESTRICTED_SERVICE_AREA";
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

void AddToPlmnList(IEPlmnList &list, const VPlmn &item)
{
    if (!std::any_of(list.plmns.begin(), list.plmns.end(), [&item](auto &i) { return DeepEqualsV(i, item); }))
        list.plmns.push_back(item);
}

VPlmn PlmnFrom(const Plmn &plmn)
{
    return VPlmn{plmn.mcc, plmn.mnc, plmn.isLongMnc};
}

NetworkSlice NssaiTo(const IENssai &v)
{
    NetworkSlice nssai{};
    for (auto &item : v.sNssais)
        nssai.slices.push_back(SNssaiTo(item));
    return nssai;
}

SingleSlice SNssaiTo(const IESNssai &v)
{
    SingleSlice sNssai{};
    sNssai.sst = v.sst;
    sNssai.sd = v.sd;
    return sNssai;
}

bool PlmnListContains(const IEPlmnList &list, Plmn item)
{
    return PlmnListContains(list, PlmnFrom(item));
}

bool PlmnListContains(const IEPlmnList &list, VPlmn item)
{
    return std::any_of(list.plmns.begin(), list.plmns.end(), [&item](auto &i) { return DeepEqualsV(i, item); });
}

bool TaiListContains(const IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai)
{
    return std::any_of(list.list.begin(), list.list.end(), [&tai](auto &i) { return TaiListContains(i, tai); });
}

bool TaiListContains(const VPartialTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai)
{
    if (list.present == 0)
    {
        auto &list0 = list.list00;
        if (DeepEqualsV(list0->plmn, tai.plmn))
        {
            if (std::any_of(list0->tacs.begin(), list0->tacs.end(), [&tai](auto &i) { return (int)i == (int)tai.tac; }))
                return true;
        }
    }
    else if (list.present == 1)
    {
        auto &list1 = list.list01;
        if (DeepEqualsV(list1->plmn, tai.plmn) && (int)list1->tac == (int)tai.tac)
            return true;
    }
    else if (list.present == 2)
    {
        auto &list2 = list.list10;
        if (std::any_of(list2->tais.begin(), list2->tais.end(), [&tai](auto &i) { return DeepEqualsV(i, tai); }))
            return true;
    }

    return false;
}

bool ServiceAreaListForbidsPlmn(const IEServiceAreaList &list, const VPlmn &plmn)
{
    return std::any_of(list.list.begin(), list.list.end(),
                       [&plmn](auto &i) { return ServiceAreaListForbidsPlmn(i, plmn); });
}

bool ServiceAreaListForbidsTai(const IEServiceAreaList &list, const VTrackingAreaIdentity &tai)
{
    return std::any_of(list.list.begin(), list.list.end(),
                       [&tai](auto &i) { return ServiceAreaListForbidsTai(i, tai); });
}

bool ServiceAreaListForbidsPlmn(const VPartialServiceAreaList &list, const VPlmn &plmn)
{
    if (list.present == 3)
    {
        if (list.list11->allowedType == EAllowedType::IN_THE_NON_ALLOWED_AREA && DeepEqualsV(list.list11->plmn, plmn))
            return true;
    }
    return false;
}

bool ServiceAreaListForbidsTai(const VPartialServiceAreaList &list, const VTrackingAreaIdentity &tai)
{
    if (ServiceAreaListForbidsPlmn(list, tai.plmn))
        return true;
    if (list.present == 0)
    {
        if (list.list00->allowedType == EAllowedType::IN_THE_NON_ALLOWED_AREA &&
            DeepEqualsV(list.list00->plmn, tai.plmn) &&
            std::any_of(list.list00->tacs.begin(), list.list00->tacs.end(),
                        [&tai](auto &i) { return (int)i == (int)tai.tac; }))
            return true;
    }
    else if (list.present == 1)
    {
        if (list.list01->allowedType == EAllowedType::IN_THE_NON_ALLOWED_AREA &&
            DeepEqualsV(list.list01->plmn, tai.plmn) && (int)list.list01->tac == (int)tai.tac)
            return true;
    }
    else if (list.present == 2)
    {
        if (list.list10->allowedType == EAllowedType::IN_THE_NON_ALLOWED_AREA &&
            std::any_of(list.list10->tais.begin(), list.list10->tais.end(),
                        [tai](auto &i) { return DeepEqualsV(i, tai); }))
            return true;
    }
    return false;
}

void AddToTaiList(IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai)
{
    if (!TaiListContains(list, tai))
    {
        VPartialTrackingAreaIdentityList ls{};
        ls.list01 = VPartialTrackingAreaIdentityList01{tai.plmn, tai.tac};
        ls.present = 1;
        list.list.push_back(ls);
    }
}

void RemoveFromTaiList(IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai)
{
    list.list.erase(std::remove_if(list.list.begin(), list.list.end(),
                                   [&tai](auto &itemList) {
                                       return itemList.present == 1 && DeepEqualsV(itemList.list01->plmn, tai.plmn) &&
                                              (int)itemList.list01->tac == (int)tai.tac;
                                   }),
                    list.list.end());

    for (auto &itemList : list.list)
    {
        if (itemList.present == 0)
        {
            auto &list0 = itemList.list00;
            if (DeepEqualsV(list0->plmn, tai.plmn))
            {
                list0->tacs.erase(std::remove_if(list0->tacs.begin(), list0->tacs.end(),
                                                 [&tai](auto tac) { return (int)tac == (int)tai.tac; }),
                                  list0->tacs.end());
            }
        }
        else if (itemList.present == 2)
        {
            auto &list2 = itemList.list10;
            list2->tais.erase(
                std::remove_if(list2->tais.begin(), list2->tais.end(), [&tai](auto &i) { return DeepEqualsV(i, tai); }),
                list2->tais.end());
        }
    }

    list.list.erase(
        std::remove_if(list.list.begin(), list.list.end(),
                       [](auto &itemList) { return itemList.present == 0 && itemList.list00->tacs.empty(); }),
        list.list.end());

    list.list.erase(
        std::remove_if(list.list.begin(), list.list.end(),
                       [](auto &itemList) { return itemList.present == 2 && itemList.list10->tais.empty(); }),
        list.list.end());
}

int TaiListSize(const IE5gsTrackingAreaIdentityList &list)
{
    int size = 0;
    for (auto &item : list.list)
        size += TaiListSize(item);
    return size;
}

int TaiListSize(const VPartialTrackingAreaIdentityList &list)
{
    size_t size = 0;
    if (list.list00.has_value())
        size += list.list00->tacs.size();
    if (list.list01.has_value())
        size++;
    if (list.list10.has_value())
        size += list.list10->tais.size();
    return static_cast<int>(size);
}

bool ServiceAreaListAllowsPlmn(const IEServiceAreaList &list, const VPlmn &plmn)
{
    return std::any_of(list.list.begin(), list.list.end(),
                       [&plmn](auto &item) { return ServiceAreaListAllowsPlmn(item, plmn); });
}

bool ServiceAreaListAllowsTai(const IEServiceAreaList &list, const VTrackingAreaIdentity &tai)
{
    return std::any_of(list.list.begin(), list.list.end(),
                       [&tai](auto &item) { return ServiceAreaListAllowsTai(item, tai); });
}

bool ServiceAreaListAllowsPlmn(const VPartialServiceAreaList &list, const VPlmn &plmn)
{
    if (list.present == 3)
    {
        if (list.list11->allowedType == EAllowedType::IN_THE_ALLOWED_AREA && DeepEqualsV(list.list11->plmn, plmn))
            return true;
    }
    return false;
}

bool ServiceAreaListAllowsTai(const VPartialServiceAreaList &list, const VTrackingAreaIdentity &tai)
{
    if (list.present == 0)
    {
        if (list.list00->allowedType == EAllowedType::IN_THE_ALLOWED_AREA && DeepEqualsV(list.list00->plmn, tai.plmn) &&
            std::any_of(list.list00->tacs.begin(), list.list00->tacs.end(),
                        [&tai](auto &i) { return (int)i == (int)tai.tac; }))
            return true;
    }
    else if (list.present == 1)
    {
        if (list.list01->allowedType == EAllowedType::IN_THE_ALLOWED_AREA && DeepEqualsV(list.list01->plmn, tai.plmn) &&
            (int)list.list01->tac == (int)tai.tac)
            return true;
    }
    else if (list.present == 2)
    {
        if (list.list10->allowedType == EAllowedType::IN_THE_ALLOWED_AREA &&
            std::any_of(list.list10->tais.begin(), list.list10->tais.end(),
                        [tai](auto &i) { return DeepEqualsV(i, tai); }))
            return true;
    }
    return false;
}

Plmn PlmnFrom(const VPlmn &plmn)
{
    Plmn res;
    res.mcc = plmn.mcc;
    res.mnc = plmn.mnc;
    res.isLongMnc = plmn.isLongMnc;
    return res;
}

void RemoveFromServiceAreaList(IEServiceAreaList &list, const VTrackingAreaIdentity &tai)
{
    std::vector<int> deletedSubLists;
    int index = 0;

    for (auto &sublist : list.list)
    {
        if (sublist.present == 0)
        {
            if (nas::utils::DeepEqualsV(sublist.list00->plmn, tai.plmn))
                ::utils::EraseWhere(sublist.list00->tacs, [&tai](auto &i) { return (int)i == (int)tai.tac; });
            if (sublist.list00->tacs.empty())
                deletedSubLists.push_back(index);
        }
        else if (sublist.present == 1)
        {
            if (nas::utils::DeepEqualsV(sublist.list01->plmn, tai.plmn) && (int)tai.tac == (int)sublist.list01->tac)
                deletedSubLists.push_back(index);
        }
        else if (sublist.present == 2)
        {
            ::utils::EraseWhere(sublist.list10->tais, [&tai](auto &i) { return nas::utils::DeepEqualsV(i, tai); });
            if (sublist.list10->tais.empty())
                deletedSubLists.push_back(index);
        }
        index++;
    }

    int deletedSoFar = 0;

    for (int i : deletedSubLists)
    {
        int indexToDelete = i - deletedSoFar;
        list.list.erase(list.list.begin() + indexToDelete);
        deletedSoFar++;
    }
}

} // namespace nas::utils

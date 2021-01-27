//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas_base.hpp"
#include "nas_values.hpp"

#include <optional>
#include <utility>

namespace nas
{

struct IEDaylightSavingTime : InformationElement4
{
    EDaylightSavingTime value{};

    IEDaylightSavingTime() = default;
    explicit IEDaylightSavingTime(EDaylightSavingTime value);

    static IEDaylightSavingTime Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEDaylightSavingTime &ie, OctetString &stream);
};

struct IEPduSessionReactivationResult : InformationElement4
{
    std::bitset<16> psi{}; // (psi[0] is reserved)

    IEPduSessionReactivationResult() = default;
    explicit IEPduSessionReactivationResult(std::bitset<16> psi);

    static IEPduSessionReactivationResult Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEPduSessionReactivationResult &ie, OctetString &stream);
};

struct IEPduAddress : InformationElement4
{
    EPduSessionType sessionType{};
    OctetString pduAddressInformation{};

    IEPduAddress() = default;
    IEPduAddress(EPduSessionType sessionType, OctetString &&pduAddressInformation);

    static IEPduAddress Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEPduAddress &ie, OctetString &stream);
};

struct IESNssai : InformationElement4
{
    octet sst{};
    std::optional<octet3> sd{};
    std::optional<octet> mappedHplmnSst{};
    std::optional<octet3> mappedHplmnSd{};

    IESNssai() = default;
    IESNssai(const octet &sst, const std::optional<octet3> &sd, const std::optional<octet> &mappedHplmnSst,
             const std::optional<octet3> &mappedHplmnSd);

    static IESNssai Decode(const OctetBuffer &stream, int length);
    static void Encode(const IESNssai &ie, OctetString &stream);
};

struct IEAdditional5gSecurityInformation : InformationElement4
{
    EHorizontalDerivationParameter hdp{};
    ERetransmissionOfInitialNasMessageRequest rinmr{};

    IEAdditional5gSecurityInformation() = default;
    IEAdditional5gSecurityInformation(EHorizontalDerivationParameter hdp,
                                      ERetransmissionOfInitialNasMessageRequest rinmr);

    static IEAdditional5gSecurityInformation Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAdditional5gSecurityInformation &ie, OctetString &stream);
};

struct IES1UeNetworkCapability : InformationElement4
{
    int b_EEA0 : 1;
    int b_128_EEA1 : 1;
    int b_128_EEA2 : 1;
    int b_128_EEA3 : 1;
    int b_EEA4 : 1;
    int b_EEA5 : 1;
    int b_EEA6 : 1;
    int b_EEA7 : 1;

    int b_EIA0 : 1;
    int b_128_EIA1 : 1;
    int b_128_EIA2 : 1;
    int b_128_EIA3 : 1;
    int b_EIA4 : 1;
    int b_EIA5 : 1;
    int b_EIA6 : 1;
    int b_EIA7 : 1;

    int b_UEA0 : 1;
    int b_UEA1 : 1;
    int b_UEA2 : 1;
    int b_UEA3 : 1;
    int b_UEA4 : 1;
    int b_UEA5 : 1;
    int b_UEA6 : 1;
    int b_UEA7 : 1;

    int b_UCS2 : 1;
    int b_UIA1 : 1;
    int b_UIA2 : 1;
    int b_UIA3 : 1;
    int b_UIA4 : 1;
    int b_UIA5 : 1;
    int b_UIA6 : 1;
    int b_UIA7 : 1;

    int b_ProSe_dd : 1;
    int b_ProSe : 1;
    int b_H_245_ASH : 1;
    int b_ACC_CSFB : 1;
    int b_LPP : 1;
    int b_LCS : 1;
    int b_1xSR_VCC : 1;
    int b_NF : 1;

    int b_ePCO : 1;
    int b_HC_CP_CIoT : 1;
    int b_ERw_oPDN : 1;
    int b_S1_U_data : 1;
    int b_UP_CIoT : 1;
    int b_CP_CIoT : 1;
    int b_Prose_relay : 1;
    int b_ProSe_dc : 1;

    int b_15_bearers : 1;
    int b_SGC : 1;
    int b_N1mode : 1;
    int b_DCNR : 1;
    int b_CP_backoff : 1;
    int b_RestrictEC : 1;
    int b_V2X_PC5 : 1;
    int b_multipleDRB : 1;

    IES1UeNetworkCapability();

    static IES1UeNetworkCapability Decode(const OctetBuffer &stream, int length);
    static void Encode(const IES1UeNetworkCapability &ie, OctetString &stream);
};

struct IEGprsTimer3 : InformationElement4
{
    int timerValue : 5;
    EGprsTimerValueUnit3 unit;

    IEGprsTimer3();

    IEGprsTimer3(int timerValue, EGprsTimerValueUnit3 unit);

    static IEGprsTimer3 Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEGprsTimer3 &ie, OctetString &stream);
};

struct IEAuthenticationFailureParameter : InformationElement4
{
    OctetString rawData{};

    IEAuthenticationFailureParameter() = default;
    explicit IEAuthenticationFailureParameter(OctetString &&rawData);

    static IEAuthenticationFailureParameter Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAuthenticationFailureParameter &ie, OctetString &stream);
};

struct IEAbba : InformationElement4
{
    OctetString rawData{};

    IEAbba() = default;
    explicit IEAbba(OctetString &&rawData);

    static IEAbba Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAbba &ie, OctetString &stream);
};

struct IES1ModeToN1ModeNasTransparentContainer : InformationElement4
{
    octet4 mac{};
    ETypeOfCipheringAlgorithm cipheringAlg{};
    ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg{};
    int keySetIdentifierIn5g{}; // 3-bits
    ETypeOfSecurityContext tsc{};
    uint8_t ncc{}; // 3-bits
    octet2 ueSecurityCapability5g{};
    std::optional<octet2> ueSecurityCapabilityEps{};

    IES1ModeToN1ModeNasTransparentContainer() = default;
    IES1ModeToN1ModeNasTransparentContainer(const octet4 &mac, ETypeOfCipheringAlgorithm cipheringAlg,
                                            ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg,
                                            int keySetIdentifierIn5G, ETypeOfSecurityContext tsc, int ncc,
                                            const octet2 &ueSecurityCapability5G,
                                            const octet2 &ueSecurityCapabilityEps);

    static IES1ModeToN1ModeNasTransparentContainer Decode(const OctetBuffer &stream, int length);
    static void Encode(const IES1ModeToN1ModeNasTransparentContainer &ie, OctetString &stream);
};

struct IEGprsTimer2 : InformationElement4
{
    octet value{};

    IEGprsTimer2() = default;
    explicit IEGprsTimer2(octet value);

    static IEGprsTimer2 Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEGprsTimer2 &ie, OctetString &stream);
};

struct IE5gSmCapability : InformationElement4
{
    EReflectiveQoS rqos{};
    EMultiHomedIPv6PduSession mh6pdu{};

    IE5gSmCapability() = default;
    IE5gSmCapability(EReflectiveQoS rqos, EMultiHomedIPv6PduSession mh6Pdu);

    static IE5gSmCapability Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gSmCapability &ie, OctetString &stream);
};

struct IEUeSecurityCapability : InformationElement4
{
    int b_5G_EA0 : 1;
    int b_128_5G_EA1 : 1;
    int b_128_5G_EA2 : 1;
    int b_128_5G_EA3 : 1;
    int b_5G_EA4 : 1;
    int b_5G_EA5 : 1;
    int b_5G_EA6 : 1;
    int b_5G_EA7 : 1;
    int b_5G_IA0 : 1;
    int b_128_5G_IA1 : 1;
    int b_128_5G_IA2 : 1;
    int b_128_5G_IA3 : 1;
    int b_5G_IA4 : 1;
    int b_5G_IA5 : 1;
    int b_5G_IA6 : 1;
    int b_5G_IA7 : 1;
    int b_EEA0 : 1;
    int b_128_EEA1 : 1;
    int b_128_EEA2 : 1;
    int b_128_EEA3 : 1;
    int b_EEA4 : 1;
    int b_EEA5 : 1;
    int b_EEA6 : 1;
    int b_EEA7 : 1;
    int b_EIA0 : 1;
    int b_128_EIA1 : 1;
    int b_128_EIA2 : 1;
    int b_128_EIA3 : 1;
    int b_EIA4 : 1;
    int b_EIA5 : 1;
    int b_EIA6 : 1;
    int b_EIA7 : 1;

    IEUeSecurityCapability();

    static IEUeSecurityCapability Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEUeSecurityCapability &ie, OctetString &stream);
};

struct IESessionAmbr : InformationElement4
{
    EUnitForSessionAmbr unitForSessionAmbrForDownlink{};
    octet2 sessionAmbrForDownlink{};
    EUnitForSessionAmbr unitForSessionAmbrForUplink{};
    octet2 sessionAmbrForUplink{};

    IESessionAmbr() = default;
    IESessionAmbr(EUnitForSessionAmbr unitForSessionAmbrForDownlink, const octet2 &sessionAmbrForDownlink,
                  EUnitForSessionAmbr unitForSessionAmbrForUplink, const octet2 &sessionAmbrForUplink);

    static IESessionAmbr Decode(const OctetBuffer &stream, int length);
    static void Encode(const IESessionAmbr &ie, OctetString &stream);
};

struct IEAuthenticationParameterAutn : InformationElement4
{
    OctetString value{};

    IEAuthenticationParameterAutn() = default;
    explicit IEAuthenticationParameterAutn(OctetString &&value);

    static IEAuthenticationParameterAutn Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAuthenticationParameterAutn &ie, OctetString &stream);
};

struct IE5gsUpdateType : InformationElement4
{
    ESmsRequested smsRequested{};
    ENgRanRadioCapabilityUpdate ngRanRcu{};

    IE5gsUpdateType() = default;
    IE5gsUpdateType(ESmsRequested smsRequested, ENgRanRadioCapabilityUpdate ngRanRcu);

    static IE5gsUpdateType Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gsUpdateType &ie, OctetString &stream);
};

struct IEUplinkDataStatus : InformationElement4
{
    std::bitset<16> psi{}; // (psi[0] is reserved)

    IEUplinkDataStatus() = default;
    explicit IEUplinkDataStatus(std::bitset<16> psi);

    static IEUplinkDataStatus Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEUplinkDataStatus &ie, OctetString &stream);
};

struct IEAdditionalInformation : InformationElement4
{
    OctetString rawData{};

    IEAdditionalInformation() = default;
    explicit IEAdditionalInformation(OctetString &&rawData);

    static IEAdditionalInformation Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAdditionalInformation &ie, OctetString &stream);
};

struct IEAuthenticationResponseParameter : InformationElement4
{
    OctetString rawData{};

    IEAuthenticationResponseParameter() = default;
    explicit IEAuthenticationResponseParameter(OctetString &&rawData);

    static IEAuthenticationResponseParameter Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAuthenticationResponseParameter &ie, OctetString &stream);
};

struct IEUeStatus : InformationElement4
{
    EEmmRegistrationStatus s1ModeReg{};
    E5gMmRegistrationStatus n1ModeReg{};

    IEUeStatus() = default;
    IEUeStatus(EEmmRegistrationStatus s1ModeReg, E5gMmRegistrationStatus n1ModeReg);

    static IEUeStatus Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEUeStatus &ie, OctetString &stream);
};

struct IE5gsRegistrationResult : InformationElement4
{
    E5gsRegistrationResult registrationResult{};
    ESmsOverNasTransportAllowed smsOverNasAllowed{};

    IE5gsRegistrationResult() = default;
    IE5gsRegistrationResult(ESmsOverNasTransportAllowed smsOverNasAllowed, E5gsRegistrationResult registrationResult);

    static IE5gsRegistrationResult Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gsRegistrationResult &ie, OctetString &stream);
};

struct IENetworkName : InformationElement4
{
    uint8_t numOfSpareBits{}; // 3-bit
    EAddCountryInitials addCi{};
    ECodingScheme codingScheme{};
    OctetString textString{};

    IENetworkName() = default;
    IENetworkName(uint8_t numOfSpareBits, EAddCountryInitials addCi, ECodingScheme codingScheme,
                  OctetString &&textString);

    static IENetworkName Decode(const OctetBuffer &stream, int length);
    static void Encode(const IENetworkName &ie, OctetString &stream);
};

struct IEUesUsageSetting : InformationElement4
{
    EUesUsageSetting value{};

    IEUesUsageSetting() = default;
    explicit IEUesUsageSetting(EUesUsageSetting value);

    static IEUesUsageSetting Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEUesUsageSetting &ie, OctetString &stream);
};

struct IEIntraN1ModeNasTransparentContainer : InformationElement4
{
    octet4 mac{};
    ETypeOfCipheringAlgorithm cipheringAlg{};
    ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg{};
    uint8_t keySetIdentifierIn5g{}; // 3-bit
    ETypeOfSecurityContext tsc{};
    EKeyAmfChangeFlag kacf{};
    octet sequenceNumber{};

    IEIntraN1ModeNasTransparentContainer() = default;
    IEIntraN1ModeNasTransparentContainer(const octet4 &mac, ETypeOfCipheringAlgorithm cipheringAlg,
                                         ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg,
                                         uint8_t keySetIdentifierIn5G, ETypeOfSecurityContext tsc,
                                         EKeyAmfChangeFlag kacf, const octet &sequenceNumber);

    static IEIntraN1ModeNasTransparentContainer Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEIntraN1ModeNasTransparentContainer &ie, OctetString &stream);
};

struct IEAllowedPduSessionStatus : InformationElement4
{
    std::bitset<16> psi{}; // (psi[0] is reserved)

    IEAllowedPduSessionStatus() = default;
    explicit IEAllowedPduSessionStatus(std::bitset<16> psi);

    static IEAllowedPduSessionStatus Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEAllowedPduSessionStatus &ie, OctetString &stream);
};

struct IEPduSessionStatus : InformationElement4
{
    std::bitset<16> psi{}; // (psi[0] is reserved)

    IEPduSessionStatus() = default;
    explicit IEPduSessionStatus(std::bitset<16> psi);

    static IEPduSessionStatus Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEPduSessionStatus &ie, OctetString &stream);
};

struct IE5gsDrxParameters : InformationElement4
{
    EDrxValue value{};

    IE5gsDrxParameters() = default;
    explicit IE5gsDrxParameters(EDrxValue value);

    static IE5gsDrxParameters Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gsDrxParameters &ie, OctetString &stream);
};

struct IE5gMmCapability : InformationElement4
{
    EEpcNasSupported s1Mode{};
    EHandoverAttachSupported hoAttach{};
    ELtePositioningProtocolCapability lpp{};

    IE5gMmCapability() = default;
    IE5gMmCapability(EEpcNasSupported s1Mode, EHandoverAttachSupported hoAttach, ELtePositioningProtocolCapability lpp);

    static IE5gMmCapability Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gMmCapability &ie, OctetString &stream);
};

struct IESmPduDnRequestContainer : InformationElement4
{
    OctetString dnSpecificIdentity{}; // UTF-8 string

    IESmPduDnRequestContainer() = default;
    explicit IESmPduDnRequestContainer(OctetString &&dnSpecificIdentity);

    static IESmPduDnRequestContainer Decode(const OctetBuffer &stream, int length);
    static void Encode(const IESmPduDnRequestContainer &ie, OctetString &stream);
};

struct IE5gsNetworkFeatureSupport : InformationElement4
{
    EImsVoPs3gpp imsVoPs3gpp{};
    EImsVoPsN3gpp imsVoPsN3gpp{};
    EEmergencyServiceSupport3gppIndicator emc{};
    EEmergencyServiceFallback3gppIndicator emf{};
    EInterworkingWithoutN26InterfaceIndicator iwkN26{};
    EMpsIndicator mpsi{};
    std::optional<EEmergencyServiceSupportNon3gppIndicator> emcn3{};
    std::optional<EMcsIndicator> mcsi{};

    IE5gsNetworkFeatureSupport() = default;
    IE5gsNetworkFeatureSupport(EImsVoPs3gpp imsVoPs3Gpp, EImsVoPsN3gpp imsVoPsN3Gpp,
                               EEmergencyServiceSupport3gppIndicator emc, EEmergencyServiceFallback3gppIndicator emf,
                               EInterworkingWithoutN26InterfaceIndicator iwkN26, EMpsIndicator mpsi,
                               const std::optional<EEmergencyServiceSupportNon3gppIndicator> &emcn3,
                               const std::optional<EMcsIndicator> &mcsi);

    static IE5gsNetworkFeatureSupport Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gsNetworkFeatureSupport &ie, OctetString &stream);
};

struct IEDnn : InformationElement4
{
    OctetString apn{}; // ASCII string

    IEDnn() = default;
    explicit IEDnn(OctetString &&apn);

    explicit IEDnn(const std::string &&apn);

    static IEDnn Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEDnn &ie, OctetString &stream);
};

struct IENssai : InformationElement4
{
    std::vector<IESNssai> sNssais{};

    IENssai() = default;
    explicit IENssai(std::vector<IESNssai> &&sNssais);

    static IENssai Decode(const OctetBuffer &stream, int length);
    static void Encode(const IENssai &ie, OctetString &stream);
};

struct IEPlmnList : InformationElement4
{
    std::vector<VPlmn> plmns{};

    IEPlmnList() = default;
    explicit IEPlmnList(std::vector<VPlmn> &&plmns);

    static IEPlmnList Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEPlmnList &ie, OctetString &stream);
};

struct IEEmergencyNumberList : InformationElement4
{
    OctetString rawData{};

    IEEmergencyNumberList() = default;
    explicit IEEmergencyNumberList(OctetString &&rawData);

    static IEEmergencyNumberList Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEEmergencyNumberList &ie, OctetString &stream);
};

struct IERejectedNssai : InformationElement4
{
    std::vector<VRejectedSNssai> list{};

    IERejectedNssai() = default;
    explicit IERejectedNssai(std::vector<VRejectedSNssai> &&list);

    static IERejectedNssai Decode(const OctetBuffer &stream, int length);
    static void Encode(const IERejectedNssai &ie, OctetString &stream);
};

struct IEServiceAreaList : InformationElement4
{
    std::vector<VPartialServiceAreaList> list{};

    IEServiceAreaList() = default;
    explicit IEServiceAreaList(std::vector<VPartialServiceAreaList> &&list);

    static IEServiceAreaList Decode(const OctetBuffer &stream, int length);
    static void Encode(const IEServiceAreaList &ie, OctetString &stream);
};

struct IE5gsTrackingAreaIdentityList : InformationElement4
{
    std::vector<VPartialTrackingAreaIdentityList> list{};

    IE5gsTrackingAreaIdentityList() = default;
    explicit IE5gsTrackingAreaIdentityList(std::vector<VPartialTrackingAreaIdentityList> &&list);

    static IE5gsTrackingAreaIdentityList Decode(const OctetBuffer &stream, int length);
    static void Encode(const IE5gsTrackingAreaIdentityList &ie, OctetString &stream);
};

} // namespace nas

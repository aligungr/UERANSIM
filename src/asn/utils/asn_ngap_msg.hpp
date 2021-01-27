//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <type_traits>

extern "C" struct ASN_NGAP_NGAP_PDU;
extern "C" struct ASN_NGAP_InitiatingMessage;
extern "C" struct ASN_NGAP_SuccessfulOutcome;
extern "C" struct ASN_NGAP_UnsuccessfulOutcome;

extern "C" struct ASN_NGAP_AMFConfigurationUpdate;
extern "C" struct ASN_NGAP_AMFConfigurationUpdateAcknowledge;
extern "C" struct ASN_NGAP_AMFConfigurationUpdateFailure;
extern "C" struct ASN_NGAP_AMFStatusIndication;
extern "C" struct ASN_NGAP_CellTrafficTrace;
extern "C" struct ASN_NGAP_DeactivateTrace;
extern "C" struct ASN_NGAP_DownlinkNASTransport;
extern "C" struct ASN_NGAP_DownlinkNonUEAssociatedNRPPaTransport;
extern "C" struct ASN_NGAP_DownlinkRANConfigurationTransfer;
extern "C" struct ASN_NGAP_DownlinkRANStatusTransfer;
extern "C" struct ASN_NGAP_DownlinkUEAssociatedNRPPaTransport;
extern "C" struct ASN_NGAP_ErrorIndication;
extern "C" struct ASN_NGAP_HandoverCancel;
extern "C" struct ASN_NGAP_HandoverCancelAcknowledge;
extern "C" struct ASN_NGAP_HandoverCommand;
extern "C" struct ASN_NGAP_HandoverFailure;
extern "C" struct ASN_NGAP_HandoverNotify;
extern "C" struct ASN_NGAP_HandoverPreparationFailure;
extern "C" struct ASN_NGAP_HandoverRequest;
extern "C" struct ASN_NGAP_HandoverRequestAcknowledge;
extern "C" struct ASN_NGAP_HandoverRequired;
extern "C" struct ASN_NGAP_InitialContextSetupFailure;
extern "C" struct ASN_NGAP_InitialContextSetupRequest;
extern "C" struct ASN_NGAP_InitialContextSetupResponse;
extern "C" struct ASN_NGAP_InitialUEMessage;
extern "C" struct ASN_NGAP_LocationReport;
extern "C" struct ASN_NGAP_LocationReportingControl;
extern "C" struct ASN_NGAP_LocationReportingFailureIndication;
extern "C" struct ASN_NGAP_NASNonDeliveryIndication;
extern "C" struct ASN_NGAP_NGReset;
extern "C" struct ASN_NGAP_NGResetAcknowledge;
extern "C" struct ASN_NGAP_NGSetupFailure;
extern "C" struct ASN_NGAP_NGSetupRequest;
extern "C" struct ASN_NGAP_NGSetupResponse;
extern "C" struct ASN_NGAP_OverloadStart;
extern "C" struct ASN_NGAP_OverloadStop;
extern "C" struct ASN_NGAP_Paging;
extern "C" struct ASN_NGAP_PathSwitchRequest;
extern "C" struct ASN_NGAP_PathSwitchRequestAcknowledge;
extern "C" struct ASN_NGAP_PathSwitchRequestFailure;
extern "C" struct ASN_NGAP_PDUSessionResourceModifyConfirm;
extern "C" struct ASN_NGAP_PDUSessionResourceModifyIndication;
extern "C" struct ASN_NGAP_PDUSessionResourceModifyRequest;
extern "C" struct ASN_NGAP_PDUSessionResourceModifyResponse;
extern "C" struct ASN_NGAP_PDUSessionResourceNotify;
extern "C" struct ASN_NGAP_PDUSessionResourceReleaseCommand;
extern "C" struct ASN_NGAP_PDUSessionResourceReleaseResponse;
extern "C" struct ASN_NGAP_PDUSessionResourceSetupRequest;
extern "C" struct ASN_NGAP_PDUSessionResourceSetupResponse;
extern "C" struct ASN_NGAP_PrivateMessage;
extern "C" struct ASN_NGAP_PWSCancelRequest;
extern "C" struct ASN_NGAP_PWSCancelResponse;
extern "C" struct ASN_NGAP_PWSFailureIndication;
extern "C" struct ASN_NGAP_PWSRestartIndication;
extern "C" struct ASN_NGAP_RANConfigurationUpdate;
extern "C" struct ASN_NGAP_RANConfigurationUpdateAcknowledge;
extern "C" struct ASN_NGAP_RANConfigurationUpdateFailure;
extern "C" struct ASN_NGAP_RerouteNASRequest;
extern "C" struct ASN_NGAP_RRCInactiveTransitionReport;
extern "C" struct ASN_NGAP_SecondaryRATDataUsageReport;
extern "C" struct ASN_NGAP_TraceFailureIndication;
extern "C" struct ASN_NGAP_TraceStart;
extern "C" struct ASN_NGAP_UEContextModificationFailure;
extern "C" struct ASN_NGAP_UEContextModificationRequest;
extern "C" struct ASN_NGAP_UEContextModificationResponse;
extern "C" struct ASN_NGAP_UEContextReleaseCommand;
extern "C" struct ASN_NGAP_UEContextReleaseComplete;
extern "C" struct ASN_NGAP_UEContextReleaseRequest;
extern "C" struct ASN_NGAP_UERadioCapabilityCheckRequest;
extern "C" struct ASN_NGAP_UERadioCapabilityCheckResponse;
extern "C" struct ASN_NGAP_UERadioCapabilityInfoIndication;
extern "C" struct ASN_NGAP_UETNLABindingReleaseRequest;
extern "C" struct ASN_NGAP_UplinkNASTransport;
extern "C" struct ASN_NGAP_UplinkNonUEAssociatedNRPPaTransport;
extern "C" struct ASN_NGAP_UplinkRANConfigurationTransfer;
extern "C" struct ASN_NGAP_UplinkRANStatusTransfer;
extern "C" struct ASN_NGAP_UplinkUEAssociatedNRPPaTransport;
extern "C" struct ASN_NGAP_WriteReplaceWarningRequest;
extern "C" struct ASN_NGAP_WriteReplaceWarningResponse;

namespace asn::ngap
{

enum class NgapMessageType
{
    AMFConfigurationUpdate,
    AMFConfigurationUpdateAcknowledge,
    AMFConfigurationUpdateFailure,
    AMFStatusIndication,
    CellTrafficTrace,
    DeactivateTrace,
    DownlinkNASTransport,
    DownlinkNonUEAssociatedNRPPaTransport,
    DownlinkRANConfigurationTransfer,
    DownlinkRANStatusTransfer,
    DownlinkUEAssociatedNRPPaTransport,
    ErrorIndication,
    HandoverCancel,
    HandoverCancelAcknowledge,
    HandoverCommand,
    HandoverFailure,
    HandoverNotify,
    HandoverPreparationFailure,
    HandoverRequest,
    HandoverRequestAcknowledge,
    HandoverRequired,
    InitialContextSetupFailure,
    InitialContextSetupRequest,
    InitialContextSetupResponse,
    InitialUEMessage,
    LocationReport,
    LocationReportingControl,
    LocationReportingFailureIndication,
    NASNonDeliveryIndication,
    NGReset,
    NGResetAcknowledge,
    NGSetupFailure,
    NGSetupRequest,
    NGSetupResponse,
    OverloadStart,
    OverloadStop,
    Paging,
    PathSwitchRequest,
    PathSwitchRequestAcknowledge,
    PathSwitchRequestFailure,
    PDUSessionResourceModifyConfirm,
    PDUSessionResourceModifyIndication,
    PDUSessionResourceModifyRequest,
    PDUSessionResourceModifyResponse,
    PDUSessionResourceNotify,
    PDUSessionResourceReleaseCommand,
    PDUSessionResourceReleaseResponse,
    PDUSessionResourceSetupRequest,
    PDUSessionResourceSetupResponse,
    PrivateMessage,
    PWSCancelRequest,
    PWSCancelResponse,
    PWSFailureIndication,
    PWSRestartIndication,
    RANConfigurationUpdate,
    RANConfigurationUpdateAcknowledge,
    RANConfigurationUpdateFailure,
    RerouteNASRequest,
    RRCInactiveTransitionReport,
    SecondaryRATDataUsageReport,
    TraceFailureIndication,
    TraceStart,
    UEContextModificationFailure,
    UEContextModificationRequest,
    UEContextModificationResponse,
    UEContextReleaseCommand,
    UEContextReleaseComplete,
    UEContextReleaseRequest,
    UERadioCapabilityCheckRequest,
    UERadioCapabilityCheckResponse,
    UERadioCapabilityInfoIndication,
    UETNLABindingReleaseRequest,
    UplinkNASTransport,
    UplinkNonUEAssociatedNRPPaTransport,
    UplinkRANConfigurationTransfer,
    UplinkRANStatusTransfer,
    UplinkUEAssociatedNRPPaTransport,
    WriteReplaceWarningRequest,
    WriteReplaceWarningResponse
};

template <NgapMessageType T>
struct NgapMessageEnumToType
{
};

template <>
struct NgapMessageEnumToType<NgapMessageType::AMFConfigurationUpdate>
{
    typedef ASN_NGAP_AMFConfigurationUpdate T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::AMFConfigurationUpdateAcknowledge>
{
    typedef ASN_NGAP_AMFConfigurationUpdateAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::AMFConfigurationUpdateFailure>
{
    typedef ASN_NGAP_AMFConfigurationUpdateFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::AMFStatusIndication>
{
    typedef ASN_NGAP_AMFStatusIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::CellTrafficTrace>
{
    typedef ASN_NGAP_CellTrafficTrace T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DeactivateTrace>
{
    typedef ASN_NGAP_DeactivateTrace T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DownlinkNASTransport>
{
    typedef ASN_NGAP_DownlinkNASTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport>
{
    typedef ASN_NGAP_DownlinkNonUEAssociatedNRPPaTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DownlinkRANConfigurationTransfer>
{
    typedef ASN_NGAP_DownlinkRANConfigurationTransfer T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DownlinkRANStatusTransfer>
{
    typedef ASN_NGAP_DownlinkRANStatusTransfer T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::DownlinkUEAssociatedNRPPaTransport>
{
    typedef ASN_NGAP_DownlinkUEAssociatedNRPPaTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::ErrorIndication>
{
    typedef ASN_NGAP_ErrorIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverCancel>
{
    typedef ASN_NGAP_HandoverCancel T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverCancelAcknowledge>
{
    typedef ASN_NGAP_HandoverCancelAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverCommand>
{
    typedef ASN_NGAP_HandoverCommand T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverFailure>
{
    typedef ASN_NGAP_HandoverFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverNotify>
{
    typedef ASN_NGAP_HandoverNotify T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverPreparationFailure>
{
    typedef ASN_NGAP_HandoverPreparationFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverRequest>
{
    typedef ASN_NGAP_HandoverRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverRequestAcknowledge>
{
    typedef ASN_NGAP_HandoverRequestAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::HandoverRequired>
{
    typedef ASN_NGAP_HandoverRequired T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::InitialContextSetupFailure>
{
    typedef ASN_NGAP_InitialContextSetupFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::InitialContextSetupRequest>
{
    typedef ASN_NGAP_InitialContextSetupRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::InitialContextSetupResponse>
{
    typedef ASN_NGAP_InitialContextSetupResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::InitialUEMessage>
{
    typedef ASN_NGAP_InitialUEMessage T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::LocationReport>
{
    typedef ASN_NGAP_LocationReport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::LocationReportingControl>
{
    typedef ASN_NGAP_LocationReportingControl T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::LocationReportingFailureIndication>
{
    typedef ASN_NGAP_LocationReportingFailureIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NASNonDeliveryIndication>
{
    typedef ASN_NGAP_NASNonDeliveryIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NGReset>
{
    typedef ASN_NGAP_NGReset T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NGResetAcknowledge>
{
    typedef ASN_NGAP_NGResetAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NGSetupFailure>
{
    typedef ASN_NGAP_NGSetupFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NGSetupRequest>
{
    typedef ASN_NGAP_NGSetupRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::NGSetupResponse>
{
    typedef ASN_NGAP_NGSetupResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::OverloadStart>
{
    typedef ASN_NGAP_OverloadStart T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::OverloadStop>
{
    typedef ASN_NGAP_OverloadStop T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::Paging>
{
    typedef ASN_NGAP_Paging T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PathSwitchRequest>
{
    typedef ASN_NGAP_PathSwitchRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PathSwitchRequestAcknowledge>
{
    typedef ASN_NGAP_PathSwitchRequestAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PathSwitchRequestFailure>
{
    typedef ASN_NGAP_PathSwitchRequestFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceModifyConfirm>
{
    typedef ASN_NGAP_PDUSessionResourceModifyConfirm T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceModifyIndication>
{
    typedef ASN_NGAP_PDUSessionResourceModifyIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceModifyRequest>
{
    typedef ASN_NGAP_PDUSessionResourceModifyRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceModifyResponse>
{
    typedef ASN_NGAP_PDUSessionResourceModifyResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceNotify>
{
    typedef ASN_NGAP_PDUSessionResourceNotify T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceReleaseCommand>
{
    typedef ASN_NGAP_PDUSessionResourceReleaseCommand T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceReleaseResponse>
{
    typedef ASN_NGAP_PDUSessionResourceReleaseResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceSetupRequest>
{
    typedef ASN_NGAP_PDUSessionResourceSetupRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PDUSessionResourceSetupResponse>
{
    typedef ASN_NGAP_PDUSessionResourceSetupResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PrivateMessage>
{
    typedef ASN_NGAP_PrivateMessage T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PWSCancelRequest>
{
    typedef ASN_NGAP_PWSCancelRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PWSCancelResponse>
{
    typedef ASN_NGAP_PWSCancelResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PWSFailureIndication>
{
    typedef ASN_NGAP_PWSFailureIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::PWSRestartIndication>
{
    typedef ASN_NGAP_PWSRestartIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::RANConfigurationUpdate>
{
    typedef ASN_NGAP_RANConfigurationUpdate T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::RANConfigurationUpdateAcknowledge>
{
    typedef ASN_NGAP_RANConfigurationUpdateAcknowledge T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::RANConfigurationUpdateFailure>
{
    typedef ASN_NGAP_RANConfigurationUpdateFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::RerouteNASRequest>
{
    typedef ASN_NGAP_RerouteNASRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::RRCInactiveTransitionReport>
{
    typedef ASN_NGAP_RRCInactiveTransitionReport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::SecondaryRATDataUsageReport>
{
    typedef ASN_NGAP_SecondaryRATDataUsageReport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::TraceFailureIndication>
{
    typedef ASN_NGAP_TraceFailureIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::TraceStart>
{
    typedef ASN_NGAP_TraceStart T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextModificationFailure>
{
    typedef ASN_NGAP_UEContextModificationFailure T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextModificationRequest>
{
    typedef ASN_NGAP_UEContextModificationRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextModificationResponse>
{
    typedef ASN_NGAP_UEContextModificationResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextReleaseCommand>
{
    typedef ASN_NGAP_UEContextReleaseCommand T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextReleaseComplete>
{
    typedef ASN_NGAP_UEContextReleaseComplete T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UEContextReleaseRequest>
{
    typedef ASN_NGAP_UEContextReleaseRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UERadioCapabilityCheckRequest>
{
    typedef ASN_NGAP_UERadioCapabilityCheckRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UERadioCapabilityCheckResponse>
{
    typedef ASN_NGAP_UERadioCapabilityCheckResponse T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UERadioCapabilityInfoIndication>
{
    typedef ASN_NGAP_UERadioCapabilityInfoIndication T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UETNLABindingReleaseRequest>
{
    typedef ASN_NGAP_UETNLABindingReleaseRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UplinkNASTransport>
{
    typedef ASN_NGAP_UplinkNASTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UplinkNonUEAssociatedNRPPaTransport>
{
    typedef ASN_NGAP_UplinkNonUEAssociatedNRPPaTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UplinkRANConfigurationTransfer>
{
    typedef ASN_NGAP_UplinkRANConfigurationTransfer T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UplinkRANStatusTransfer>
{
    typedef ASN_NGAP_UplinkRANStatusTransfer T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::UplinkUEAssociatedNRPPaTransport>
{
    typedef ASN_NGAP_UplinkUEAssociatedNRPPaTransport T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::WriteReplaceWarningRequest>
{
    typedef ASN_NGAP_WriteReplaceWarningRequest T;
};
template <>
struct NgapMessageEnumToType<NgapMessageType::WriteReplaceWarningResponse>
{
    typedef ASN_NGAP_WriteReplaceWarningResponse T;
};

template <typename T>
struct NgapMessageTypeToEnum;

template <>
struct NgapMessageTypeToEnum<ASN_NGAP_AMFConfigurationUpdate>
{
    enum
    {
        V = (int)NgapMessageType::AMFConfigurationUpdate
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_AMFConfigurationUpdateAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::AMFConfigurationUpdateAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_AMFConfigurationUpdateFailure>
{
    enum
    {
        V = (int)NgapMessageType::AMFConfigurationUpdateFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_AMFStatusIndication>
{
    enum
    {
        V = (int)NgapMessageType::AMFStatusIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_CellTrafficTrace>
{
    enum
    {
        V = (int)NgapMessageType::CellTrafficTrace
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DeactivateTrace>
{
    enum
    {
        V = (int)NgapMessageType::DeactivateTrace
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DownlinkNASTransport>
{
    enum
    {
        V = (int)NgapMessageType::DownlinkNASTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DownlinkNonUEAssociatedNRPPaTransport>
{
    enum
    {
        V = (int)NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DownlinkRANConfigurationTransfer>
{
    enum
    {
        V = (int)NgapMessageType::DownlinkRANConfigurationTransfer
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DownlinkRANStatusTransfer>
{
    enum
    {
        V = (int)NgapMessageType::DownlinkRANStatusTransfer
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_DownlinkUEAssociatedNRPPaTransport>
{
    enum
    {
        V = (int)NgapMessageType::DownlinkUEAssociatedNRPPaTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_ErrorIndication>
{
    enum
    {
        V = (int)NgapMessageType::ErrorIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverCancel>
{
    enum
    {
        V = (int)NgapMessageType::HandoverCancel
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverCancelAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::HandoverCancelAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverCommand>
{
    enum
    {
        V = (int)NgapMessageType::HandoverCommand
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverFailure>
{
    enum
    {
        V = (int)NgapMessageType::HandoverFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverNotify>
{
    enum
    {
        V = (int)NgapMessageType::HandoverNotify
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverPreparationFailure>
{
    enum
    {
        V = (int)NgapMessageType::HandoverPreparationFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverRequest>
{
    enum
    {
        V = (int)NgapMessageType::HandoverRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverRequestAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::HandoverRequestAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_HandoverRequired>
{
    enum
    {
        V = (int)NgapMessageType::HandoverRequired
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_InitialContextSetupFailure>
{
    enum
    {
        V = (int)NgapMessageType::InitialContextSetupFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_InitialContextSetupRequest>
{
    enum
    {
        V = (int)NgapMessageType::InitialContextSetupRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_InitialContextSetupResponse>
{
    enum
    {
        V = (int)NgapMessageType::InitialContextSetupResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_InitialUEMessage>
{
    enum
    {
        V = (int)NgapMessageType::InitialUEMessage
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_LocationReport>
{
    enum
    {
        V = (int)NgapMessageType::LocationReport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_LocationReportingControl>
{
    enum
    {
        V = (int)NgapMessageType::LocationReportingControl
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_LocationReportingFailureIndication>
{
    enum
    {
        V = (int)NgapMessageType::LocationReportingFailureIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NASNonDeliveryIndication>
{
    enum
    {
        V = (int)NgapMessageType::NASNonDeliveryIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NGReset>
{
    enum
    {
        V = (int)NgapMessageType::NGReset
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NGResetAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::NGResetAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NGSetupFailure>
{
    enum
    {
        V = (int)NgapMessageType::NGSetupFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NGSetupRequest>
{
    enum
    {
        V = (int)NgapMessageType::NGSetupRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_NGSetupResponse>
{
    enum
    {
        V = (int)NgapMessageType::NGSetupResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_OverloadStart>
{
    enum
    {
        V = (int)NgapMessageType::OverloadStart
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_OverloadStop>
{
    enum
    {
        V = (int)NgapMessageType::OverloadStop
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_Paging>
{
    enum
    {
        V = (int)NgapMessageType::Paging
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PathSwitchRequest>
{
    enum
    {
        V = (int)NgapMessageType::PathSwitchRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PathSwitchRequestAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::PathSwitchRequestAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PathSwitchRequestFailure>
{
    enum
    {
        V = (int)NgapMessageType::PathSwitchRequestFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceModifyConfirm>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceModifyConfirm
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceModifyIndication>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceModifyIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceModifyRequest>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceModifyRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceModifyResponse>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceModifyResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceNotify>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceNotify
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceReleaseCommand>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceReleaseCommand
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceReleaseResponse>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceReleaseResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceSetupRequest>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceSetupRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PDUSessionResourceSetupResponse>
{
    enum
    {
        V = (int)NgapMessageType::PDUSessionResourceSetupResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PrivateMessage>
{
    enum
    {
        V = (int)NgapMessageType::PrivateMessage
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PWSCancelRequest>
{
    enum
    {
        V = (int)NgapMessageType::PWSCancelRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PWSCancelResponse>
{
    enum
    {
        V = (int)NgapMessageType::PWSCancelResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PWSFailureIndication>
{
    enum
    {
        V = (int)NgapMessageType::PWSFailureIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_PWSRestartIndication>
{
    enum
    {
        V = (int)NgapMessageType::PWSRestartIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_RANConfigurationUpdate>
{
    enum
    {
        V = (int)NgapMessageType::RANConfigurationUpdate
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_RANConfigurationUpdateAcknowledge>
{
    enum
    {
        V = (int)NgapMessageType::RANConfigurationUpdateAcknowledge
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_RANConfigurationUpdateFailure>
{
    enum
    {
        V = (int)NgapMessageType::RANConfigurationUpdateFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_RerouteNASRequest>
{
    enum
    {
        V = (int)NgapMessageType::RerouteNASRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_RRCInactiveTransitionReport>
{
    enum
    {
        V = (int)NgapMessageType::RRCInactiveTransitionReport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_SecondaryRATDataUsageReport>
{
    enum
    {
        V = (int)NgapMessageType::SecondaryRATDataUsageReport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_TraceFailureIndication>
{
    enum
    {
        V = (int)NgapMessageType::TraceFailureIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_TraceStart>
{
    enum
    {
        V = (int)NgapMessageType::TraceStart
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextModificationFailure>
{
    enum
    {
        V = (int)NgapMessageType::UEContextModificationFailure
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextModificationRequest>
{
    enum
    {
        V = (int)NgapMessageType::UEContextModificationRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextModificationResponse>
{
    enum
    {
        V = (int)NgapMessageType::UEContextModificationResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextReleaseCommand>
{
    enum
    {
        V = (int)NgapMessageType::UEContextReleaseCommand
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextReleaseComplete>
{
    enum
    {
        V = (int)NgapMessageType::UEContextReleaseComplete
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UEContextReleaseRequest>
{
    enum
    {
        V = (int)NgapMessageType::UEContextReleaseRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UERadioCapabilityCheckRequest>
{
    enum
    {
        V = (int)NgapMessageType::UERadioCapabilityCheckRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UERadioCapabilityCheckResponse>
{
    enum
    {
        V = (int)NgapMessageType::UERadioCapabilityCheckResponse
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UERadioCapabilityInfoIndication>
{
    enum
    {
        V = (int)NgapMessageType::UERadioCapabilityInfoIndication
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UETNLABindingReleaseRequest>
{
    enum
    {
        V = (int)NgapMessageType::UETNLABindingReleaseRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UplinkNASTransport>
{
    enum
    {
        V = (int)NgapMessageType::UplinkNASTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UplinkNonUEAssociatedNRPPaTransport>
{
    enum
    {
        V = (int)NgapMessageType::UplinkNonUEAssociatedNRPPaTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UplinkRANConfigurationTransfer>
{
    enum
    {
        V = (int)NgapMessageType::UplinkRANConfigurationTransfer
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UplinkRANStatusTransfer>
{
    enum
    {
        V = (int)NgapMessageType::UplinkRANStatusTransfer
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_UplinkUEAssociatedNRPPaTransport>
{
    enum
    {
        V = (int)NgapMessageType::UplinkUEAssociatedNRPPaTransport
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_WriteReplaceWarningRequest>
{
    enum
    {
        V = (int)NgapMessageType::WriteReplaceWarningRequest
    };
};
template <>
struct NgapMessageTypeToEnum<ASN_NGAP_WriteReplaceWarningResponse>
{
    enum
    {
        V = (int)NgapMessageType::WriteReplaceWarningResponse
    };
};

struct NgapMessageToDescription_InitiatingMessage
{
    typedef ASN_NGAP_InitiatingMessage T;
};

struct NgapMessageToDescription_SuccessfulOutcome
{
    typedef ASN_NGAP_SuccessfulOutcome T;
};

struct NgapMessageToDescription_UnsuccessfulOutcome
{
    typedef ASN_NGAP_UnsuccessfulOutcome T;
};

template <typename T>
struct NgapMessageToDescription;

template <>
struct NgapMessageToDescription<ASN_NGAP_AMFConfigurationUpdate> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_AMFStatusIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_CellTrafficTrace> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DeactivateTrace> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DownlinkNASTransport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DownlinkNonUEAssociatedNRPPaTransport>
    : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DownlinkRANConfigurationTransfer> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DownlinkRANStatusTransfer> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_DownlinkUEAssociatedNRPPaTransport>
    : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_ErrorIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverCancel> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverNotify> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverRequired> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_InitialContextSetupRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_InitialUEMessage> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_LocationReport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_LocationReportingControl> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_LocationReportingFailureIndication>
    : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NASNonDeliveryIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NGReset> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NGSetupRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_OverloadStart> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_OverloadStop> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_Paging> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PathSwitchRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceModifyIndication>
    : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceModifyRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceNotify> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceReleaseCommand> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceSetupRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PrivateMessage> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PWSCancelRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PWSFailureIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PWSRestartIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_RANConfigurationUpdate> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_RerouteNASRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_RRCInactiveTransitionReport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_SecondaryRATDataUsageReport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_TraceFailureIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_TraceStart> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextModificationRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextReleaseCommand> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextReleaseRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UERadioCapabilityCheckRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UERadioCapabilityInfoIndication> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UETNLABindingReleaseRequest> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UplinkNASTransport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UplinkNonUEAssociatedNRPPaTransport>
    : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UplinkRANConfigurationTransfer> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UplinkRANStatusTransfer> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UplinkUEAssociatedNRPPaTransport> : NgapMessageToDescription_InitiatingMessage
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_WriteReplaceWarningRequest> : NgapMessageToDescription_InitiatingMessage
{
};

template <>
struct NgapMessageToDescription<ASN_NGAP_AMFConfigurationUpdateAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverCancelAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverCommand> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverRequestAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_InitialContextSetupResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NGResetAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NGSetupResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PathSwitchRequestAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceModifyConfirm> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceModifyResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceReleaseResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PDUSessionResourceSetupResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PWSCancelResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_RANConfigurationUpdateAcknowledge> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextModificationResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextReleaseComplete> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UERadioCapabilityCheckResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_WriteReplaceWarningResponse> : NgapMessageToDescription_SuccessfulOutcome
{
};

template <>
struct NgapMessageToDescription<ASN_NGAP_AMFConfigurationUpdateFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_HandoverPreparationFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_InitialContextSetupFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_NGSetupFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_PathSwitchRequestFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_RANConfigurationUpdateFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};
template <>
struct NgapMessageToDescription<ASN_NGAP_UEContextModificationFailure> : NgapMessageToDescription_UnsuccessfulOutcome
{
};

template <typename T>
struct NgapMessageToIeType
{
    typedef typename std::remove_reference<decltype(*T{}.protocolIEs.list.array[0])>::type value;
};

template <typename T>
struct NgapMessageToIeUnionType
{
    typedef decltype(typename NgapMessageToIeType<T>::value{}.value.choice) value;
};

} // namespace asn::ngap
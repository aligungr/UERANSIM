//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "asn_ngap.hpp"
#include "asn_utils.hpp"

#include <functional>

#include <ASN_NGAP_NGAP-PDU.h>

#include <ASN_NGAP_InitiatingMessage.h>
#include <ASN_NGAP_SuccessfulOutcome.h>
#include <ASN_NGAP_UnsuccessfulOutcome.h>

#include <ASN_NGAP_AMFConfigurationUpdate.h>
#include <ASN_NGAP_AMFConfigurationUpdateAcknowledge.h>
#include <ASN_NGAP_AMFConfigurationUpdateFailure.h>
#include <ASN_NGAP_AMFStatusIndication.h>
#include <ASN_NGAP_CellTrafficTrace.h>
#include <ASN_NGAP_DeactivateTrace.h>
#include <ASN_NGAP_DownlinkNASTransport.h>
#include <ASN_NGAP_DownlinkNonUEAssociatedNRPPaTransport.h>
#include <ASN_NGAP_DownlinkRANConfigurationTransfer.h>
#include <ASN_NGAP_DownlinkRANStatusTransfer.h>
#include <ASN_NGAP_DownlinkUEAssociatedNRPPaTransport.h>
#include <ASN_NGAP_ErrorIndication.h>
#include <ASN_NGAP_HandoverCancel.h>
#include <ASN_NGAP_HandoverCancelAcknowledge.h>
#include <ASN_NGAP_HandoverCommand.h>
#include <ASN_NGAP_HandoverFailure.h>
#include <ASN_NGAP_HandoverNotify.h>
#include <ASN_NGAP_HandoverPreparationFailure.h>
#include <ASN_NGAP_HandoverRequest.h>
#include <ASN_NGAP_HandoverRequestAcknowledge.h>
#include <ASN_NGAP_HandoverRequired.h>
#include <ASN_NGAP_InitialContextSetupFailure.h>
#include <ASN_NGAP_InitialContextSetupRequest.h>
#include <ASN_NGAP_InitialContextSetupResponse.h>
#include <ASN_NGAP_InitialUEMessage.h>
#include <ASN_NGAP_LocationReport.h>
#include <ASN_NGAP_LocationReportingControl.h>
#include <ASN_NGAP_LocationReportingFailureIndication.h>
#include <ASN_NGAP_NASNonDeliveryIndication.h>
#include <ASN_NGAP_NGReset.h>
#include <ASN_NGAP_NGResetAcknowledge.h>
#include <ASN_NGAP_NGSetupFailure.h>
#include <ASN_NGAP_NGSetupRequest.h>
#include <ASN_NGAP_NGSetupResponse.h>
#include <ASN_NGAP_OverloadStart.h>
#include <ASN_NGAP_OverloadStop.h>
#include <ASN_NGAP_PDUSessionResourceModifyConfirm.h>
#include <ASN_NGAP_PDUSessionResourceModifyIndication.h>
#include <ASN_NGAP_PDUSessionResourceModifyRequest.h>
#include <ASN_NGAP_PDUSessionResourceModifyResponse.h>
#include <ASN_NGAP_PDUSessionResourceNotify.h>
#include <ASN_NGAP_PDUSessionResourceReleaseCommand.h>
#include <ASN_NGAP_PDUSessionResourceReleaseResponse.h>
#include <ASN_NGAP_PDUSessionResourceSetupRequest.h>
#include <ASN_NGAP_PDUSessionResourceSetupResponse.h>
#include <ASN_NGAP_PWSCancelRequest.h>
#include <ASN_NGAP_PWSCancelResponse.h>
#include <ASN_NGAP_PWSFailureIndication.h>
#include <ASN_NGAP_PWSRestartIndication.h>
#include <ASN_NGAP_Paging.h>
#include <ASN_NGAP_PathSwitchRequest.h>
#include <ASN_NGAP_PathSwitchRequestAcknowledge.h>
#include <ASN_NGAP_PathSwitchRequestFailure.h>
#include <ASN_NGAP_PrivateMessage.h>
#include <ASN_NGAP_RANConfigurationUpdate.h>
#include <ASN_NGAP_RANConfigurationUpdateAcknowledge.h>
#include <ASN_NGAP_RANConfigurationUpdateFailure.h>
#include <ASN_NGAP_RRCInactiveTransitionReport.h>
#include <ASN_NGAP_RerouteNASRequest.h>
#include <ASN_NGAP_SecondaryRATDataUsageReport.h>
#include <ASN_NGAP_TraceFailureIndication.h>
#include <ASN_NGAP_TraceStart.h>
#include <ASN_NGAP_UEContextModificationFailure.h>
#include <ASN_NGAP_UEContextModificationRequest.h>
#include <ASN_NGAP_UEContextModificationResponse.h>
#include <ASN_NGAP_UEContextReleaseCommand.h>
#include <ASN_NGAP_UEContextReleaseComplete.h>
#include <ASN_NGAP_UEContextReleaseRequest.h>
#include <ASN_NGAP_UERadioCapabilityCheckRequest.h>
#include <ASN_NGAP_UERadioCapabilityCheckResponse.h>
#include <ASN_NGAP_UERadioCapabilityInfoIndication.h>
#include <ASN_NGAP_UETNLABindingReleaseRequest.h>
#include <ASN_NGAP_UplinkNASTransport.h>
#include <ASN_NGAP_UplinkNonUEAssociatedNRPPaTransport.h>
#include <ASN_NGAP_UplinkRANConfigurationTransfer.h>
#include <ASN_NGAP_UplinkRANStatusTransfer.h>
#include <ASN_NGAP_UplinkUEAssociatedNRPPaTransport.h>
#include <ASN_NGAP_WriteReplaceWarningRequest.h>
#include <ASN_NGAP_WriteReplaceWarningResponse.h>

#include <ASN_NGAP_ProtocolIE-Field.h>

namespace asn::ngap
{

ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_InitiatingMessage *desc)
{
    auto pdu = asn::New<ASN_NGAP_NGAP_PDU>();
    pdu->present = ASN_NGAP_NGAP_PDU_PR_initiatingMessage;
    pdu->choice.initiatingMessage = desc;
    return pdu;
}

ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_SuccessfulOutcome *desc)
{
    auto pdu = asn::New<ASN_NGAP_NGAP_PDU>();
    pdu->present = ASN_NGAP_NGAP_PDU_PR_successfulOutcome;
    pdu->choice.successfulOutcome = desc;
    return pdu;
}

ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_UnsuccessfulOutcome *desc)
{
    auto pdu = asn::New<ASN_NGAP_NGAP_PDU>();
    pdu->present = ASN_NGAP_NGAP_PDU_PR_unsuccessfulOutcome;
    pdu->choice.unsuccessfulOutcome = desc;
    return pdu;
}

void *NewDescFromMessageType(NgapMessageType type, void *&pOutDescription)
{
    int pduDescription = GetPduDescription(type);
    assert(pduDescription >= 0 && pduDescription <= 2);

    if (pduDescription == 0)
    {
        auto *desc = asn::New<ASN_NGAP_InitiatingMessage>();
        desc->procedureCode = GetProcedureCode(type);
        desc->criticality = GetProcedureCriticality(type);
        desc->value.present = static_cast<ASN_NGAP_InitiatingMessage__value_PR>(GetProcedurePresent(type));
        pOutDescription = desc;

        switch (type)
        {
        case NgapMessageType::AMFConfigurationUpdate:
            return &desc->value.choice.AMFConfigurationUpdate;
        case NgapMessageType::HandoverCancel:
            return &desc->value.choice.HandoverCancel;
        case NgapMessageType::HandoverRequired:
            return &desc->value.choice.HandoverRequired;
        case NgapMessageType::HandoverRequest:
            return &desc->value.choice.HandoverRequest;
        case NgapMessageType::InitialContextSetupRequest:
            return &desc->value.choice.InitialContextSetupRequest;
        case NgapMessageType::NGReset:
            return &desc->value.choice.NGReset;
        case NgapMessageType::NGSetupRequest:
            return &desc->value.choice.NGSetupRequest;
        case NgapMessageType::PathSwitchRequest:
            return &desc->value.choice.PathSwitchRequest;
        case NgapMessageType::PDUSessionResourceModifyRequest:
            return &desc->value.choice.PDUSessionResourceModifyRequest;
        case NgapMessageType::PDUSessionResourceModifyIndication:
            return &desc->value.choice.PDUSessionResourceModifyIndication;
        case NgapMessageType::PDUSessionResourceReleaseCommand:
            return &desc->value.choice.PDUSessionResourceReleaseCommand;
        case NgapMessageType::PDUSessionResourceSetupRequest:
            return &desc->value.choice.PDUSessionResourceSetupRequest;
        case NgapMessageType::PWSCancelRequest:
            return &desc->value.choice.PWSCancelRequest;
        case NgapMessageType::RANConfigurationUpdate:
            return &desc->value.choice.RANConfigurationUpdate;
        case NgapMessageType::UEContextModificationRequest:
            return &desc->value.choice.UEContextModificationRequest;
        case NgapMessageType::UEContextReleaseCommand:
            return &desc->value.choice.UEContextReleaseCommand;
        case NgapMessageType::UERadioCapabilityCheckRequest:
            return &desc->value.choice.UERadioCapabilityCheckRequest;
        case NgapMessageType::WriteReplaceWarningRequest:
            return &desc->value.choice.WriteReplaceWarningRequest;
        case NgapMessageType::AMFStatusIndication:
            return &desc->value.choice.AMFStatusIndication;
        case NgapMessageType::CellTrafficTrace:
            return &desc->value.choice.CellTrafficTrace;
        case NgapMessageType::DeactivateTrace:
            return &desc->value.choice.DeactivateTrace;
        case NgapMessageType::DownlinkNASTransport:
            return &desc->value.choice.DownlinkNASTransport;
        case NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport:
            return &desc->value.choice.DownlinkNonUEAssociatedNRPPaTransport;
        case NgapMessageType::DownlinkRANConfigurationTransfer:
            return &desc->value.choice.DownlinkRANConfigurationTransfer;
        case NgapMessageType::DownlinkRANStatusTransfer:
            return &desc->value.choice.DownlinkRANStatusTransfer;
        case NgapMessageType::DownlinkUEAssociatedNRPPaTransport:
            return &desc->value.choice.DownlinkUEAssociatedNRPPaTransport;
        case NgapMessageType::ErrorIndication:
            return &desc->value.choice.ErrorIndication;
        case NgapMessageType::HandoverNotify:
            return &desc->value.choice.HandoverNotify;
        case NgapMessageType::InitialUEMessage:
            return &desc->value.choice.InitialUEMessage;
        case NgapMessageType::LocationReport:
            return &desc->value.choice.LocationReport;
        case NgapMessageType::LocationReportingControl:
            return &desc->value.choice.LocationReportingControl;
        case NgapMessageType::LocationReportingFailureIndication:
            return &desc->value.choice.LocationReportingFailureIndication;
        case NgapMessageType::NASNonDeliveryIndication:
            return &desc->value.choice.NASNonDeliveryIndication;
        case NgapMessageType::OverloadStart:
            return &desc->value.choice.OverloadStart;
        case NgapMessageType::OverloadStop:
            return &desc->value.choice.OverloadStop;
        case NgapMessageType::Paging:
            return &desc->value.choice.Paging;
        case NgapMessageType::PDUSessionResourceNotify:
            return &desc->value.choice.PDUSessionResourceNotify;
        case NgapMessageType::PrivateMessage:
            return &desc->value.choice.PrivateMessage;
        case NgapMessageType::PWSFailureIndication:
            return &desc->value.choice.PWSFailureIndication;
        case NgapMessageType::PWSRestartIndication:
            return &desc->value.choice.PWSRestartIndication;
        case NgapMessageType::RerouteNASRequest:
            return &desc->value.choice.RerouteNASRequest;
        case NgapMessageType::RRCInactiveTransitionReport:
            return &desc->value.choice.RRCInactiveTransitionReport;
        case NgapMessageType::SecondaryRATDataUsageReport:
            return &desc->value.choice.SecondaryRATDataUsageReport;
        case NgapMessageType::TraceFailureIndication:
            return &desc->value.choice.TraceFailureIndication;
        case NgapMessageType::TraceStart:
            return &desc->value.choice.TraceStart;
        case NgapMessageType::UEContextReleaseRequest:
            return &desc->value.choice.UEContextReleaseRequest;
        case NgapMessageType::UERadioCapabilityInfoIndication:
            return &desc->value.choice.UERadioCapabilityInfoIndication;
        case NgapMessageType::UETNLABindingReleaseRequest:
            return &desc->value.choice.UETNLABindingReleaseRequest;
        case NgapMessageType::UplinkNASTransport:
            return &desc->value.choice.UplinkNASTransport;
        case NgapMessageType::UplinkNonUEAssociatedNRPPaTransport:
            return &desc->value.choice.UplinkNonUEAssociatedNRPPaTransport;
        case NgapMessageType::UplinkRANConfigurationTransfer:
            return &desc->value.choice.UplinkRANConfigurationTransfer;
        case NgapMessageType::UplinkRANStatusTransfer:
            return &desc->value.choice.UplinkRANStatusTransfer;
        case NgapMessageType::UplinkUEAssociatedNRPPaTransport:
            return &desc->value.choice.UplinkUEAssociatedNRPPaTransport;
        default:
            assert(false);
        }
    }

    if (pduDescription == 1)
    {
        auto *desc = asn::New<ASN_NGAP_SuccessfulOutcome>();
        desc->procedureCode = GetProcedureCode(type);
        desc->criticality = GetProcedureCriticality(type);
        desc->value.present = static_cast<ASN_NGAP_SuccessfulOutcome__value_PR>(GetProcedurePresent(type));
        pOutDescription = desc;

        switch (type)
        {
        case NgapMessageType::AMFConfigurationUpdateAcknowledge:
            return &desc->value.choice.AMFConfigurationUpdateAcknowledge;
        case NgapMessageType::HandoverCancelAcknowledge:
            return &desc->value.choice.HandoverCancelAcknowledge;
        case NgapMessageType::HandoverCommand:
            return &desc->value.choice.HandoverCommand;
        case NgapMessageType::HandoverRequestAcknowledge:
            return &desc->value.choice.HandoverRequestAcknowledge;
        case NgapMessageType::InitialContextSetupResponse:
            return &desc->value.choice.InitialContextSetupResponse;
        case NgapMessageType::NGResetAcknowledge:
            return &desc->value.choice.NGResetAcknowledge;
        case NgapMessageType::NGSetupResponse:
            return &desc->value.choice.NGSetupResponse;
        case NgapMessageType::PathSwitchRequestAcknowledge:
            return &desc->value.choice.PathSwitchRequestAcknowledge;
        case NgapMessageType::PDUSessionResourceModifyResponse:
            return &desc->value.choice.PDUSessionResourceModifyResponse;
        case NgapMessageType::PDUSessionResourceModifyConfirm:
            return &desc->value.choice.PDUSessionResourceModifyConfirm;
        case NgapMessageType::PDUSessionResourceReleaseResponse:
            return &desc->value.choice.PDUSessionResourceReleaseResponse;
        case NgapMessageType::PDUSessionResourceSetupResponse:
            return &desc->value.choice.PDUSessionResourceSetupResponse;
        case NgapMessageType::PWSCancelResponse:
            return &desc->value.choice.PWSCancelResponse;
        case NgapMessageType::RANConfigurationUpdateAcknowledge:
            return &desc->value.choice.RANConfigurationUpdateAcknowledge;
        case NgapMessageType::UEContextModificationResponse:
            return &desc->value.choice.UEContextModificationResponse;
        case NgapMessageType::UEContextReleaseComplete:
            return &desc->value.choice.UEContextReleaseComplete;
        case NgapMessageType::UERadioCapabilityCheckResponse:
            return &desc->value.choice.UERadioCapabilityCheckResponse;
        case NgapMessageType::WriteReplaceWarningResponse:
            return &desc->value.choice.WriteReplaceWarningResponse;
        default:
            assert(false);
        }
    }

    if (pduDescription == 2)
    {
        auto *desc = asn::New<ASN_NGAP_UnsuccessfulOutcome>();
        desc->procedureCode = GetProcedureCode(type);
        desc->criticality = GetProcedureCriticality(type);
        desc->value.present = static_cast<ASN_NGAP_UnsuccessfulOutcome__value_PR>(GetProcedurePresent(type));
        pOutDescription = desc;

        switch (type)
        {
        case NgapMessageType::AMFConfigurationUpdateFailure:
            return &desc->value.choice.AMFConfigurationUpdateFailure;
        case NgapMessageType::HandoverPreparationFailure:
            return &desc->value.choice.HandoverPreparationFailure;
        case NgapMessageType::HandoverFailure:
            return &desc->value.choice.HandoverFailure;
        case NgapMessageType::InitialContextSetupFailure:
            return &desc->value.choice.InitialContextSetupFailure;
        case NgapMessageType::NGSetupFailure:
            return &desc->value.choice.NGSetupFailure;
        case NgapMessageType::PathSwitchRequestFailure:
            return &desc->value.choice.PathSwitchRequestFailure;
        case NgapMessageType::RANConfigurationUpdateFailure:
            return &desc->value.choice.RANConfigurationUpdateFailure;
        case NgapMessageType::UEContextModificationFailure:
            return &desc->value.choice.UEContextModificationFailure;
        default:
            assert(false);
        }
    }

    assert(false);
}

int GetProcedureCode(NgapMessageType messageType)
{
    switch (messageType)
    {
    case NgapMessageType::AMFConfigurationUpdate:
    case NgapMessageType::AMFConfigurationUpdateAcknowledge:
    case NgapMessageType::AMFConfigurationUpdateFailure:
        return 0;
    case NgapMessageType::AMFStatusIndication:
        return 1;
    case NgapMessageType::CellTrafficTrace:
        return 2;
    case NgapMessageType::DeactivateTrace:
        return 3;
    case NgapMessageType::DownlinkNASTransport:
        return 4;
    case NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport:
        return 5;
    case NgapMessageType::DownlinkRANConfigurationTransfer:
        return 6;
    case NgapMessageType::DownlinkRANStatusTransfer:
        return 7;
    case NgapMessageType::DownlinkUEAssociatedNRPPaTransport:
        return 8;
    case NgapMessageType::ErrorIndication:
        return 9;
    case NgapMessageType::HandoverCancel:
    case NgapMessageType::HandoverCancelAcknowledge:
        return 10;
    case NgapMessageType::HandoverNotify:
        return 11;
    case NgapMessageType::HandoverPreparationFailure:
    case NgapMessageType::HandoverRequired:
    case NgapMessageType::HandoverCommand:
        return 12;
    case NgapMessageType::HandoverRequest:
    case NgapMessageType::HandoverRequestAcknowledge:
    case NgapMessageType::HandoverFailure:
        return 13;
    case NgapMessageType::InitialContextSetupFailure:
    case NgapMessageType::InitialContextSetupRequest:
    case NgapMessageType::InitialContextSetupResponse:
        return 14;
    case NgapMessageType::InitialUEMessage:
        return 15;
    case NgapMessageType::LocationReportingControl:
        return 16;
    case NgapMessageType::LocationReportingFailureIndication:
        return 17;
    case NgapMessageType::LocationReport:
        return 18;
    case NgapMessageType::NASNonDeliveryIndication:
        return 19;
    case NgapMessageType::NGReset:
    case NgapMessageType::NGResetAcknowledge:
        return 20;
    case NgapMessageType::NGSetupFailure:
    case NgapMessageType::NGSetupRequest:
    case NgapMessageType::NGSetupResponse:
        return 21;
    case NgapMessageType::OverloadStart:
        return 22;
    case NgapMessageType::OverloadStop:
        return 23;
    case NgapMessageType::Paging:
        return 24;
    case NgapMessageType::PathSwitchRequest:
    case NgapMessageType::PathSwitchRequestAcknowledge:
    case NgapMessageType::PathSwitchRequestFailure:
        return 25;
    case NgapMessageType::PDUSessionResourceModifyRequest:
    case NgapMessageType::PDUSessionResourceModifyResponse:
        return 26;
    case NgapMessageType::PDUSessionResourceModifyConfirm:
    case NgapMessageType::PDUSessionResourceModifyIndication:
        return 27;
    case NgapMessageType::PDUSessionResourceReleaseCommand:
    case NgapMessageType::PDUSessionResourceReleaseResponse:
        return 28;
    case NgapMessageType::PDUSessionResourceSetupRequest:
    case NgapMessageType::PDUSessionResourceSetupResponse:
        return 29;
    case NgapMessageType::PDUSessionResourceNotify:
        return 30;
    case NgapMessageType::PrivateMessage:
        return 31;
    case NgapMessageType::PWSCancelRequest:
    case NgapMessageType::PWSCancelResponse:
        return 32;
    case NgapMessageType::PWSFailureIndication:
        return 33;
    case NgapMessageType::PWSRestartIndication:
        return 34;
    case NgapMessageType::RANConfigurationUpdate:
    case NgapMessageType::RANConfigurationUpdateAcknowledge:
    case NgapMessageType::RANConfigurationUpdateFailure:
        return 35;
    case NgapMessageType::RerouteNASRequest:
        return 36;
    case NgapMessageType::RRCInactiveTransitionReport:
        return 37;
    case NgapMessageType::TraceFailureIndication:
        return 38;
    case NgapMessageType::TraceStart:
        return 39;
    case NgapMessageType::UEContextModificationFailure:
    case NgapMessageType::UEContextModificationRequest:
    case NgapMessageType::UEContextModificationResponse:
        return 40;
    case NgapMessageType::UEContextReleaseCommand:
    case NgapMessageType::UEContextReleaseComplete:
    case NgapMessageType::UEContextReleaseRequest:
        return 42;
    case NgapMessageType::UERadioCapabilityCheckRequest:
    case NgapMessageType::UERadioCapabilityCheckResponse:
    case NgapMessageType::UERadioCapabilityInfoIndication:
        return 44;
    case NgapMessageType::UETNLABindingReleaseRequest:
        return 45;
    case NgapMessageType::UplinkNASTransport:
        return 46;
    case NgapMessageType::UplinkNonUEAssociatedNRPPaTransport:
        return 47;
    case NgapMessageType::UplinkRANConfigurationTransfer:
        return 48;
    case NgapMessageType::UplinkRANStatusTransfer:
        return 49;
    case NgapMessageType::UplinkUEAssociatedNRPPaTransport:
        return 50;
    case NgapMessageType::WriteReplaceWarningRequest:
    case NgapMessageType::WriteReplaceWarningResponse:
        return 51;
    case NgapMessageType::SecondaryRATDataUsageReport:
        return 52;
    default:
        assert(false);
    }
}

int GetProcedureCriticality(NgapMessageType messageType)
{
    switch (messageType)
    {
    case NgapMessageType::AMFConfigurationUpdate:
    case NgapMessageType::AMFConfigurationUpdateAcknowledge:
    case NgapMessageType::AMFConfigurationUpdateFailure:
    case NgapMessageType::HandoverCancel:
    case NgapMessageType::HandoverCancelAcknowledge:
    case NgapMessageType::HandoverCommand:
    case NgapMessageType::HandoverFailure:
    case NgapMessageType::HandoverPreparationFailure:
    case NgapMessageType::HandoverRequest:
    case NgapMessageType::HandoverRequestAcknowledge:
    case NgapMessageType::HandoverRequired:
    case NgapMessageType::InitialContextSetupFailure:
    case NgapMessageType::InitialContextSetupRequest:
    case NgapMessageType::InitialContextSetupResponse:
    case NgapMessageType::NGReset:
    case NgapMessageType::NGResetAcknowledge:
    case NgapMessageType::NGSetupFailure:
    case NgapMessageType::NGSetupRequest:
    case NgapMessageType::NGSetupResponse:
    case NgapMessageType::OverloadStop:
    case NgapMessageType::PathSwitchRequest:
    case NgapMessageType::PathSwitchRequestAcknowledge:
    case NgapMessageType::PathSwitchRequestFailure:
    case NgapMessageType::PDUSessionResourceModifyConfirm:
    case NgapMessageType::PDUSessionResourceModifyIndication:
    case NgapMessageType::PDUSessionResourceModifyRequest:
    case NgapMessageType::PDUSessionResourceModifyResponse:
    case NgapMessageType::PDUSessionResourceReleaseCommand:
    case NgapMessageType::PDUSessionResourceReleaseResponse:
    case NgapMessageType::PDUSessionResourceSetupRequest:
    case NgapMessageType::PDUSessionResourceSetupResponse:
    case NgapMessageType::PWSCancelRequest:
    case NgapMessageType::PWSCancelResponse:
    case NgapMessageType::RANConfigurationUpdate:
    case NgapMessageType::RANConfigurationUpdateAcknowledge:
    case NgapMessageType::RANConfigurationUpdateFailure:
    case NgapMessageType::RerouteNASRequest:
    case NgapMessageType::UEContextModificationFailure:
    case NgapMessageType::UEContextModificationRequest:
    case NgapMessageType::UEContextModificationResponse:
    case NgapMessageType::UEContextReleaseCommand:
    case NgapMessageType::UEContextReleaseComplete:
    case NgapMessageType::UERadioCapabilityCheckRequest:
    case NgapMessageType::UERadioCapabilityCheckResponse:
    case NgapMessageType::WriteReplaceWarningRequest:
    case NgapMessageType::WriteReplaceWarningResponse:
        return 0;
    case NgapMessageType::AMFStatusIndication:
    case NgapMessageType::CellTrafficTrace:
    case NgapMessageType::DeactivateTrace:
    case NgapMessageType::DownlinkNASTransport:
    case NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport:
    case NgapMessageType::DownlinkRANConfigurationTransfer:
    case NgapMessageType::DownlinkRANStatusTransfer:
    case NgapMessageType::DownlinkUEAssociatedNRPPaTransport:
    case NgapMessageType::ErrorIndication:
    case NgapMessageType::HandoverNotify:
    case NgapMessageType::InitialUEMessage:
    case NgapMessageType::LocationReport:
    case NgapMessageType::LocationReportingControl:
    case NgapMessageType::LocationReportingFailureIndication:
    case NgapMessageType::NASNonDeliveryIndication:
    case NgapMessageType::OverloadStart:
    case NgapMessageType::Paging:
    case NgapMessageType::PDUSessionResourceNotify:
    case NgapMessageType::PrivateMessage:
    case NgapMessageType::PWSFailureIndication:
    case NgapMessageType::PWSRestartIndication:
    case NgapMessageType::RRCInactiveTransitionReport:
    case NgapMessageType::SecondaryRATDataUsageReport:
    case NgapMessageType::TraceFailureIndication:
    case NgapMessageType::TraceStart:
    case NgapMessageType::UEContextReleaseRequest:
    case NgapMessageType::UERadioCapabilityInfoIndication:
    case NgapMessageType::UETNLABindingReleaseRequest:
    case NgapMessageType::UplinkNASTransport:
    case NgapMessageType::UplinkNonUEAssociatedNRPPaTransport:
    case NgapMessageType::UplinkRANConfigurationTransfer:
    case NgapMessageType::UplinkRANStatusTransfer:
    case NgapMessageType::UplinkUEAssociatedNRPPaTransport:
        return 1;
    default:
        return 2;
    }
}

int GetProcedurePresent(NgapMessageType messageType)
{
    switch (messageType)
    {
    case NgapMessageType::AMFConfigurationUpdate:
        return ASN_NGAP_InitiatingMessage__value_PR_AMFConfigurationUpdate;
    case NgapMessageType::HandoverCancel:
        return ASN_NGAP_InitiatingMessage__value_PR_HandoverCancel;
    case NgapMessageType::HandoverRequired:
        return ASN_NGAP_InitiatingMessage__value_PR_HandoverRequired;
    case NgapMessageType::HandoverRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_HandoverRequest;
    case NgapMessageType::InitialContextSetupRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_InitialContextSetupRequest;
    case NgapMessageType::NGReset:
        return ASN_NGAP_InitiatingMessage__value_PR_NGReset;
    case NgapMessageType::NGSetupRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_NGSetupRequest;
    case NgapMessageType::PathSwitchRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_PathSwitchRequest;
    case NgapMessageType::PDUSessionResourceModifyRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceModifyRequest;
    case NgapMessageType::PDUSessionResourceModifyIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceModifyIndication;
    case NgapMessageType::PDUSessionResourceReleaseCommand:
        return ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceReleaseCommand;
    case NgapMessageType::PDUSessionResourceSetupRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceSetupRequest;
    case NgapMessageType::PWSCancelRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_PWSCancelRequest;
    case NgapMessageType::RANConfigurationUpdate:
        return ASN_NGAP_InitiatingMessage__value_PR_RANConfigurationUpdate;
    case NgapMessageType::UEContextModificationRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_UEContextModificationRequest;
    case NgapMessageType::UEContextReleaseCommand:
        return ASN_NGAP_InitiatingMessage__value_PR_UEContextReleaseCommand;
    case NgapMessageType::UERadioCapabilityCheckRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_UERadioCapabilityCheckRequest;
    case NgapMessageType::WriteReplaceWarningRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_WriteReplaceWarningRequest;
    case NgapMessageType::AMFStatusIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_AMFStatusIndication;
    case NgapMessageType::CellTrafficTrace:
        return ASN_NGAP_InitiatingMessage__value_PR_CellTrafficTrace;
    case NgapMessageType::DeactivateTrace:
        return ASN_NGAP_InitiatingMessage__value_PR_DeactivateTrace;
    case NgapMessageType::DownlinkNASTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_DownlinkNASTransport;
    case NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_DownlinkNonUEAssociatedNRPPaTransport;
    case NgapMessageType::DownlinkRANConfigurationTransfer:
        return ASN_NGAP_InitiatingMessage__value_PR_DownlinkRANConfigurationTransfer;
    case NgapMessageType::DownlinkRANStatusTransfer:
        return ASN_NGAP_InitiatingMessage__value_PR_DownlinkRANStatusTransfer;
    case NgapMessageType::DownlinkUEAssociatedNRPPaTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_DownlinkUEAssociatedNRPPaTransport;
    case NgapMessageType::ErrorIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_ErrorIndication;
    case NgapMessageType::HandoverNotify:
        return ASN_NGAP_InitiatingMessage__value_PR_HandoverNotify;
    case NgapMessageType::InitialUEMessage:
        return ASN_NGAP_InitiatingMessage__value_PR_InitialUEMessage;
    case NgapMessageType::LocationReport:
        return ASN_NGAP_InitiatingMessage__value_PR_LocationReport;
    case NgapMessageType::LocationReportingControl:
        return ASN_NGAP_InitiatingMessage__value_PR_LocationReportingControl;
    case NgapMessageType::LocationReportingFailureIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_LocationReportingFailureIndication;
    case NgapMessageType::NASNonDeliveryIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_NASNonDeliveryIndication;
    case NgapMessageType::OverloadStart:
        return ASN_NGAP_InitiatingMessage__value_PR_OverloadStart;
    case NgapMessageType::OverloadStop:
        return ASN_NGAP_InitiatingMessage__value_PR_OverloadStop;
    case NgapMessageType::Paging:
        return ASN_NGAP_InitiatingMessage__value_PR_Paging;
    case NgapMessageType::PDUSessionResourceNotify:
        return ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceNotify;
    case NgapMessageType::PrivateMessage:
        return ASN_NGAP_InitiatingMessage__value_PR_PrivateMessage;
    case NgapMessageType::PWSFailureIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_PWSFailureIndication;
    case NgapMessageType::PWSRestartIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_PWSRestartIndication;
    case NgapMessageType::RerouteNASRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_RerouteNASRequest;
    case NgapMessageType::RRCInactiveTransitionReport:
        return ASN_NGAP_InitiatingMessage__value_PR_RRCInactiveTransitionReport;
    case NgapMessageType::SecondaryRATDataUsageReport:
        return ASN_NGAP_InitiatingMessage__value_PR_SecondaryRATDataUsageReport;
    case NgapMessageType::TraceFailureIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_TraceFailureIndication;
    case NgapMessageType::TraceStart:
        return ASN_NGAP_InitiatingMessage__value_PR_TraceStart;
    case NgapMessageType::UEContextReleaseRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_UEContextReleaseRequest;
    case NgapMessageType::UERadioCapabilityInfoIndication:
        return ASN_NGAP_InitiatingMessage__value_PR_UERadioCapabilityInfoIndication;
    case NgapMessageType::UETNLABindingReleaseRequest:
        return ASN_NGAP_InitiatingMessage__value_PR_UETNLABindingReleaseRequest;
    case NgapMessageType::UplinkNASTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_UplinkNASTransport;
    case NgapMessageType::UplinkNonUEAssociatedNRPPaTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_UplinkNonUEAssociatedNRPPaTransport;
    case NgapMessageType::UplinkRANConfigurationTransfer:
        return ASN_NGAP_InitiatingMessage__value_PR_UplinkRANConfigurationTransfer;
    case NgapMessageType::UplinkRANStatusTransfer:
        return ASN_NGAP_InitiatingMessage__value_PR_UplinkRANStatusTransfer;
    case NgapMessageType::UplinkUEAssociatedNRPPaTransport:
        return ASN_NGAP_InitiatingMessage__value_PR_UplinkUEAssociatedNRPPaTransport;

    case NgapMessageType::AMFConfigurationUpdateAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_AMFConfigurationUpdateAcknowledge;
    case NgapMessageType::HandoverCancelAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_HandoverCancelAcknowledge;
    case NgapMessageType::HandoverCommand:
        return ASN_NGAP_SuccessfulOutcome__value_PR_HandoverCommand;
    case NgapMessageType::HandoverRequestAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_HandoverRequestAcknowledge;
    case NgapMessageType::InitialContextSetupResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_InitialContextSetupResponse;
    case NgapMessageType::NGResetAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_NGResetAcknowledge;
    case NgapMessageType::NGSetupResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_NGSetupResponse;
    case NgapMessageType::PathSwitchRequestAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PathSwitchRequestAcknowledge;
    case NgapMessageType::PDUSessionResourceModifyResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PDUSessionResourceModifyResponse;
    case NgapMessageType::PDUSessionResourceModifyConfirm:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PDUSessionResourceModifyConfirm;
    case NgapMessageType::PDUSessionResourceReleaseResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PDUSessionResourceReleaseResponse;
    case NgapMessageType::PDUSessionResourceSetupResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PDUSessionResourceSetupResponse;
    case NgapMessageType::PWSCancelResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_PWSCancelResponse;
    case NgapMessageType::RANConfigurationUpdateAcknowledge:
        return ASN_NGAP_SuccessfulOutcome__value_PR_RANConfigurationUpdateAcknowledge;
    case NgapMessageType::UEContextModificationResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_UEContextModificationResponse;
    case NgapMessageType::UEContextReleaseComplete:
        return ASN_NGAP_SuccessfulOutcome__value_PR_UEContextReleaseComplete;
    case NgapMessageType::UERadioCapabilityCheckResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_UERadioCapabilityCheckResponse;
    case NgapMessageType::WriteReplaceWarningResponse:
        return ASN_NGAP_SuccessfulOutcome__value_PR_WriteReplaceWarningResponse;

    case NgapMessageType::AMFConfigurationUpdateFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_AMFConfigurationUpdateFailure;
    case NgapMessageType::HandoverPreparationFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_HandoverPreparationFailure;
    case NgapMessageType::HandoverFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_HandoverFailure;
    case NgapMessageType::InitialContextSetupFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_InitialContextSetupFailure;
    case NgapMessageType::NGSetupFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_NGSetupFailure;
    case NgapMessageType::PathSwitchRequestFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_PathSwitchRequestFailure;
    case NgapMessageType::RANConfigurationUpdateFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_RANConfigurationUpdateFailure;
    case NgapMessageType::UEContextModificationFailure:
        return ASN_NGAP_UnsuccessfulOutcome__value_PR_UEContextModificationFailure;

    default:
        assert(false);
    }
}

int GetPduDescription(NgapMessageType messageType)
{
    switch (messageType)
    {
    case NgapMessageType::AMFConfigurationUpdate:
    case NgapMessageType::HandoverCancel:
    case NgapMessageType::HandoverRequired:
    case NgapMessageType::HandoverRequest:
    case NgapMessageType::InitialContextSetupRequest:
    case NgapMessageType::NGReset:
    case NgapMessageType::NGSetupRequest:
    case NgapMessageType::PathSwitchRequest:
    case NgapMessageType::PDUSessionResourceModifyRequest:
    case NgapMessageType::PDUSessionResourceModifyIndication:
    case NgapMessageType::PDUSessionResourceReleaseCommand:
    case NgapMessageType::PDUSessionResourceSetupRequest:
    case NgapMessageType::PWSCancelRequest:
    case NgapMessageType::RANConfigurationUpdate:
    case NgapMessageType::UEContextModificationRequest:
    case NgapMessageType::UEContextReleaseCommand:
    case NgapMessageType::UERadioCapabilityCheckRequest:
    case NgapMessageType::WriteReplaceWarningRequest:
    case NgapMessageType::AMFStatusIndication:
    case NgapMessageType::CellTrafficTrace:
    case NgapMessageType::DeactivateTrace:
    case NgapMessageType::DownlinkNASTransport:
    case NgapMessageType::DownlinkNonUEAssociatedNRPPaTransport:
    case NgapMessageType::DownlinkRANConfigurationTransfer:
    case NgapMessageType::DownlinkRANStatusTransfer:
    case NgapMessageType::DownlinkUEAssociatedNRPPaTransport:
    case NgapMessageType::ErrorIndication:
    case NgapMessageType::HandoverNotify:
    case NgapMessageType::InitialUEMessage:
    case NgapMessageType::LocationReport:
    case NgapMessageType::LocationReportingControl:
    case NgapMessageType::LocationReportingFailureIndication:
    case NgapMessageType::NASNonDeliveryIndication:
    case NgapMessageType::OverloadStart:
    case NgapMessageType::OverloadStop:
    case NgapMessageType::Paging:
    case NgapMessageType::PDUSessionResourceNotify:
    case NgapMessageType::PrivateMessage:
    case NgapMessageType::PWSFailureIndication:
    case NgapMessageType::PWSRestartIndication:
    case NgapMessageType::RerouteNASRequest:
    case NgapMessageType::RRCInactiveTransitionReport:
    case NgapMessageType::SecondaryRATDataUsageReport:
    case NgapMessageType::TraceFailureIndication:
    case NgapMessageType::TraceStart:
    case NgapMessageType::UEContextReleaseRequest:
    case NgapMessageType::UERadioCapabilityInfoIndication:
    case NgapMessageType::UETNLABindingReleaseRequest:
    case NgapMessageType::UplinkNASTransport:
    case NgapMessageType::UplinkNonUEAssociatedNRPPaTransport:
    case NgapMessageType::UplinkRANConfigurationTransfer:
    case NgapMessageType::UplinkRANStatusTransfer:
    case NgapMessageType::UplinkUEAssociatedNRPPaTransport:
        return 0;

    case NgapMessageType::AMFConfigurationUpdateAcknowledge:
    case NgapMessageType::HandoverCancelAcknowledge:
    case NgapMessageType::HandoverCommand:
    case NgapMessageType::HandoverRequestAcknowledge:
    case NgapMessageType::InitialContextSetupResponse:
    case NgapMessageType::NGResetAcknowledge:
    case NgapMessageType::NGSetupResponse:
    case NgapMessageType::PathSwitchRequestAcknowledge:
    case NgapMessageType::PDUSessionResourceModifyResponse:
    case NgapMessageType::PDUSessionResourceModifyConfirm:
    case NgapMessageType::PDUSessionResourceReleaseResponse:
    case NgapMessageType::PDUSessionResourceSetupResponse:
    case NgapMessageType::PWSCancelResponse:
    case NgapMessageType::RANConfigurationUpdateAcknowledge:
    case NgapMessageType::UEContextModificationResponse:
    case NgapMessageType::UEContextReleaseComplete:
    case NgapMessageType::UERadioCapabilityCheckResponse:
    case NgapMessageType::WriteReplaceWarningResponse:
        return 1;

    case NgapMessageType::AMFConfigurationUpdateFailure:
    case NgapMessageType::HandoverPreparationFailure:
    case NgapMessageType::HandoverFailure:
    case NgapMessageType::InitialContextSetupFailure:
    case NgapMessageType::NGSetupFailure:
    case NgapMessageType::PathSwitchRequestFailure:
    case NgapMessageType::RANConfigurationUpdateFailure:
    case NgapMessageType::UEContextModificationFailure:
        return 2;

    default:
        assert(false);
    }
}

struct IeFieldInfo
{
    unsigned int ieIdOffset;
    unsigned int ieCriticalityOffset;
    unsigned int ieStructSize;
    void *listPtr;
    unsigned int presOffset;
    unsigned int choiceOffset;
    unsigned int presSize;
    unsigned int memberIndex;
    void *protocolIeContainerPtr;
    asn_anonymous_set_ *list;
};

static bool GetProtocolIeInfo(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType, IeFieldInfo &info)
{
    // This function assumes all ASN structs are "C++ standard layout".
    // Therefore no problem is expected since the structs are already standard layout.

    // TODO: assert for Little Endian (this asssumption is used for presentEnumSize and related)

    asn_TYPE_descriptor_t *desc;
    void *ptr;

    if (pdu.present == ASN_NGAP_NGAP_PDU_PR_initiatingMessage)
    {
        desc = &asn_DEF_ASN_NGAP_InitiatingMessage;
        ptr = pdu.choice.initiatingMessage;
    }
    else if (pdu.present == ASN_NGAP_NGAP_PDU_PR_successfulOutcome)
    {
        desc = &asn_DEF_ASN_NGAP_SuccessfulOutcome;
        ptr = pdu.choice.successfulOutcome;
    }
    else if (pdu.present == ASN_NGAP_NGAP_PDU_PR_unsuccessfulOutcome)
    {
        desc = &asn_DEF_ASN_NGAP_UnsuccessfulOutcome;
        ptr = pdu.choice.unsuccessfulOutcome;
    }
    else
        return false;

    ptr = reinterpret_cast<int8_t *>(ptr) + desc->elements[2].memb_offset;
    desc = desc->elements[2].type;

    auto members = desc->elements;
    unsigned memberCount = desc->elements_count;

    auto choiceSpecs = reinterpret_cast<const asn_CHOICE_specifics_t *>(desc->specifics);

    unsigned presentEnumSize = choiceSpecs->pres_size;

    auto presPtr = reinterpret_cast<int8_t *>(ptr) + choiceSpecs->pres_offset;

    uint64_t presentValue = 0;
    for (unsigned i = 0; i < presentEnumSize; i++)
        presentValue += (*(presPtr + i) & 0xFF) << (i * 8);

    if (presentValue == 0)
        return false;

    ptr = reinterpret_cast<int8_t *>(ptr) + members[presentValue - 1].memb_offset;
    desc = members[presentValue - 1].type;

    members = desc->elements;
    memberCount = desc->elements_count;

    ptr = reinterpret_cast<int8_t *>(ptr) + members[0].memb_offset;
    desc = members[0].type;

    info.protocolIeContainerPtr = ptr;

    members = desc->elements;
    memberCount = desc->elements_count;

    ptr = reinterpret_cast<int8_t *>(ptr) + members[0].memb_offset;
    desc = members[0].type;

    members = desc->elements;
    memberCount = desc->elements_count;

    info.ieIdOffset = members[0].memb_offset;
    info.ieCriticalityOffset = members[1].memb_offset;
    info.ieStructSize = reinterpret_cast<const asn_SEQUENCE_specifics_t *>(desc->specifics)->struct_size;

    info.listPtr = ptr;

    info.presOffset = members[2].memb_offset;
    info.choiceOffset = members[2].memb_offset;

    ptr = reinterpret_cast<int8_t *>(ptr) + members[2].memb_offset;
    desc = members[2].type;

    members = desc->elements;
    memberCount = desc->elements_count;

    choiceSpecs = reinterpret_cast<const asn_CHOICE_specifics_t *>(desc->specifics);
    info.presOffset += choiceSpecs->pres_offset;

    info.presSize = choiceSpecs->pres_size;

    info.memberIndex = ~0;

    for (unsigned i = 0; i < memberCount; i++)
    {
        if (members[i].type == &ieType)
        {
            // NOT: Birden fazla varsa ilk buludğunu alıyor.
            info.memberIndex = i;
            break;
        }
    }

    if (info.memberIndex == ~0U)
        return false;

    ptr = reinterpret_cast<int8_t *>(ptr) + members[info.memberIndex].memb_offset;
    desc = members[info.memberIndex].type;

    info.choiceOffset += members[info.memberIndex].memb_offset;

    info.list = reinterpret_cast<asn_anonymous_set_ *>(info.listPtr);

    return true;
}

bool IsProtocolIeUsable(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType)
{
    IeFieldInfo inf{};
    return GetProtocolIeInfo(pdu, ieType, inf);
}

void *FindProtocolIeInPdu(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType, int protocolIeId)
{
    IeFieldInfo inf{};
    if (!GetProtocolIeInfo(pdu, ieType, inf))
        return nullptr;

    for (int i = 0; i < inf.list->count; i++)
    {
        void *item = inf.list->array[i];
        ASN_NGAP_ProtocolIE_ID_t ieId =
            *reinterpret_cast<ASN_NGAP_ProtocolIE_ID_t *>((reinterpret_cast<int8_t *>(item) + inf.ieIdOffset));
        if (ieId == protocolIeId)
        {
            auto *valuePlace = (void *)(reinterpret_cast<int8_t *>(item) + inf.choiceOffset);
            return valuePlace;
        }
    }
    return nullptr;
}

bool AddProtocolIeIfUsable(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType, int protocolIeId,
                           int criticality, const std::function<void(void *)> &ieCreator)
{
    IeFieldInfo inf{};
    if (!GetProtocolIeInfo(pdu, ieType, inf))
        return false;

    /* Create and add new protocol IE field */
    {
        void *newIe = calloc(1, inf.ieStructSize);

        *reinterpret_cast<ASN_NGAP_ProtocolIE_ID_t *>((reinterpret_cast<int8_t *>(newIe) + inf.ieIdOffset)) =
            protocolIeId;
        *reinterpret_cast<ASN_NGAP_Criticality_t *>((reinterpret_cast<int8_t *>(newIe) + inf.ieCriticalityOffset)) =
            criticality;

        auto *newPresPtr = (reinterpret_cast<int8_t *>(newIe) + inf.presOffset);

        for (unsigned i = 0; i < inf.presSize; i++)
            newPresPtr[i] = ((inf.memberIndex + 1) >> (i * 8)) & 0xFF;

        auto *valuePlace = (void *)(reinterpret_cast<int8_t *>(newIe) + inf.choiceOffset);
        ieCreator(valuePlace);

        ASN_SEQUENCE_ADD(inf.protocolIeContainerPtr, newIe);

        // Sorting according to present value. See "asn::ngap::AddProtocolIe" function.
        std::sort(inf.list->array, inf.list->array + inf.list->count, [&inf](void *a, void *b) {
            auto *aPresPtr = reinterpret_cast<int8_t *>(a) + inf.presOffset;
            auto *bPresPtr = reinterpret_cast<int8_t *>(b) + inf.presOffset;

            uint64_t aPresValue = 0, bPresValue = 0;
            for (unsigned i = 0; i < inf.presSize; i++)
            {
                aPresValue += (*(aPresPtr + i) & 0xFF) << (i * 8);
                bPresValue += (*(bPresPtr + i) & 0xFF) << (i * 8);
            }

            return aPresValue < bPresValue;
        });
    }

    return true;
}

} // namespace asn::ngap
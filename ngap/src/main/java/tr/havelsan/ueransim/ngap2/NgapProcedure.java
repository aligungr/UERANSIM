/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.ngap2;

import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

@Deprecated
public enum NgapProcedure {
    AMFConfigurationUpdateAcknowledge,
    AMFConfigurationUpdateFailure,
    AMFConfigurationUpdate,
    AMFStatusIndication,
    CellTrafficTrace,
    DeactivateTrace,
    DownlinkNASTransport,
    DownlinkNonUEAssociatedNRPPaTransport,
    DownlinkRANConfigurationTransfer,
    DownlinkRANStatusTransfer,
    DownlinkUEAssociatedNRPPaTransport,
    ErrorIndication,
    HandoverCancelAcknowledge,
    HandoverCancel,
    HandoverCommand,
    HandoverFailure,
    HandoverNotify,
    HandoverPreparationFailure,
    HandoverRequestAcknowledge,
    HandoverRequest,
    HandoverRequired,
    InitialContextSetupFailure,
    InitialContextSetupRequest,
    InitialContextSetupResponse,
    InitialUEMessage,
    LocationReportingControl,
    LocationReportingFailureIndication,
    LocationReport,
    NASNonDeliveryIndication,
    NGAP_Message,
    NGResetAcknowledge,
    NGReset,
    NGSetupFailure,
    NGSetupRequest,
    NGSetupResponse,
    OverloadStart,
    OverloadStop,
    Paging,
    PathSwitchRequestAcknowledge,
    PathSwitchRequestFailure,
    PathSwitchRequest,
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
    RANConfigurationUpdateAcknowledge,
    RANConfigurationUpdateFailure,
    RANConfigurationUpdate,
    RerouteNASRequest,
    RRCInactiveTransitionReport,
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
    WriteReplaceWarningResponse;

    public NgapPduDescription pduDescription() {
        switch (this) {
            case AMFConfigurationUpdate:
            case AMFStatusIndication:
            case CellTrafficTrace:
            case DeactivateTrace:
            case DownlinkNASTransport:
            case DownlinkNonUEAssociatedNRPPaTransport:
            case DownlinkRANConfigurationTransfer:
            case DownlinkRANStatusTransfer:
            case DownlinkUEAssociatedNRPPaTransport:
            case ErrorIndication:
            case HandoverCancel:
            case HandoverNotify:
            case HandoverRequired:
            case HandoverRequest:
            case InitialContextSetupRequest:
            case InitialUEMessage:
            case LocationReport:
            case LocationReportingControl:
            case LocationReportingFailureIndication:
            case NASNonDeliveryIndication:
            case NGReset:
            case NGSetupRequest:
            case OverloadStart:
            case OverloadStop:
            case Paging:
            case PathSwitchRequest:
            case PDUSessionResourceModifyRequest:
            case PDUSessionResourceModifyIndication:
            case PDUSessionResourceNotify:
            case PDUSessionResourceReleaseCommand:
            case PDUSessionResourceSetupRequest:
            case PrivateMessage:
            case PWSCancelRequest:
            case PWSFailureIndication:
            case PWSRestartIndication:
            case RANConfigurationUpdate:
            case RerouteNASRequest:
            case RRCInactiveTransitionReport:
            case TraceFailureIndication:
            case TraceStart:
            case UEContextModificationRequest:
            case UEContextReleaseCommand:
            case UEContextReleaseRequest:
            case UERadioCapabilityCheckRequest:
            case UERadioCapabilityInfoIndication:
            case UETNLABindingReleaseRequest:
            case UplinkNASTransport:
            case UplinkNonUEAssociatedNRPPaTransport:
            case UplinkRANConfigurationTransfer:
            case UplinkRANStatusTransfer:
            case UplinkUEAssociatedNRPPaTransport:
            case WriteReplaceWarningRequest:
                return NgapPduDescription.INITIATING_MESSAGE;

            case AMFConfigurationUpdateAcknowledge:
            case HandoverCancelAcknowledge:
            case HandoverCommand:
            case HandoverRequestAcknowledge:
            case InitialContextSetupResponse:
            case NGResetAcknowledge:
            case NGSetupResponse:
            case PathSwitchRequestAcknowledge:
            case PDUSessionResourceModifyResponse:
            case PDUSessionResourceModifyConfirm:
            case PDUSessionResourceReleaseResponse:
            case PDUSessionResourceSetupResponse:
            case PWSCancelResponse:
            case RANConfigurationUpdateAcknowledge:
            case UEContextModificationResponse:
            case UEContextReleaseComplete:
            case UERadioCapabilityCheckResponse:
            case WriteReplaceWarningResponse:
                return NgapPduDescription.SUCCESSFUL_OUTCOME;

            case AMFConfigurationUpdateFailure:
            case HandoverPreparationFailure:
            case HandoverFailure:
            case InitialContextSetupFailure:
            case NGSetupFailure:
            case PathSwitchRequestFailure:
            case RANConfigurationUpdateFailure:
            case UEContextModificationFailure:
                return NgapPduDescription.UNSUCCESSFUL_OUTCOME;

            default:
                Logging.error(Tag.NGAP_INTERNAL, "no pdu description for: %s", this);
                throw new RuntimeException();
        }
    }

    public NgapCriticality procedureCriticality() {
        switch (this) {
            case AMFStatusIndication:
            case CellTrafficTrace:
            case DeactivateTrace:
            case DownlinkNASTransport:
            case DownlinkNonUEAssociatedNRPPaTransport:
            case DownlinkRANConfigurationTransfer:
            case DownlinkRANStatusTransfer:
            case DownlinkUEAssociatedNRPPaTransport:
            case ErrorIndication:
            case InitialUEMessage:
            case LocationReportingControl:
            case LocationReportingFailureIndication:
            case LocationReport:
            case NASNonDeliveryIndication:
            case OverloadStart:
            case Paging:
            case PDUSessionResourceNotify:
            case PrivateMessage:
            case PWSFailureIndication:
            case PWSRestartIndication:
            case RRCInactiveTransitionReport:
            case TraceFailureIndication:
            case TraceStart:
            case UEContextReleaseRequest:
            case UERadioCapabilityInfoIndication:
            case UETNLABindingReleaseRequest:
            case UplinkNASTransport:
            case UplinkNonUEAssociatedNRPPaTransport:
            case UplinkRANConfigurationTransfer:
            case UplinkRANStatusTransfer:
            case UplinkUEAssociatedNRPPaTransport:
            case HandoverNotify:
                return NgapCriticality.IGNORE;

            case AMFConfigurationUpdateAcknowledge:
            case AMFConfigurationUpdateFailure:
            case AMFConfigurationUpdate:
            case HandoverCancel:
            case InitialContextSetupFailure:
            case InitialContextSetupRequest:
            case InitialContextSetupResponse:
            case NGReset:
            case NGSetupFailure:
            case NGSetupRequest:
            case NGSetupResponse:
            case OverloadStop:
            case PDUSessionResourceModifyConfirm:
            case PDUSessionResourceModifyIndication:
            case PDUSessionResourceModifyRequest:
            case PDUSessionResourceModifyResponse:
            case PDUSessionResourceReleaseCommand:
            case PDUSessionResourceReleaseResponse:
            case PDUSessionResourceSetupRequest:
            case PDUSessionResourceSetupResponse:
            case PWSCancelRequest:
            case PWSCancelResponse:
            case RANConfigurationUpdateAcknowledge:
            case RANConfigurationUpdateFailure:
            case RANConfigurationUpdate:
            case RerouteNASRequest:
            case UEContextModificationFailure:
            case UEContextModificationRequest:
            case UEContextModificationResponse:
            case UEContextReleaseCommand:
            case UEContextReleaseComplete:
            case UERadioCapabilityCheckRequest:
            case UERadioCapabilityCheckResponse:
            case WriteReplaceWarningRequest:
            case WriteReplaceWarningResponse:
            case HandoverCancelAcknowledge:
            case NGResetAcknowledge:
            case PathSwitchRequestAcknowledge:
            case PathSwitchRequestFailure:
            case PathSwitchRequest:
            case HandoverRequired:
            case HandoverRequestAcknowledge:
            case HandoverPreparationFailure:
            case HandoverFailure:
            case HandoverCommand:
            case HandoverRequest:
                return NgapCriticality.REJECT;
            default:
                Logging.error(Tag.NGAP_INTERNAL, "no criticality for: %s", this);
                throw new RuntimeException();
        }
    }

    public int procedureCode() {
        switch (this) {
            case AMFConfigurationUpdateAcknowledge:
            case AMFConfigurationUpdateFailure:
            case AMFConfigurationUpdate:
                return Values.NGAP_Constants__id_AMFConfigurationUpdate;
            case AMFStatusIndication:
                return Values.NGAP_Constants__id_AMFStatusIndication;
            case CellTrafficTrace:
                return Values.NGAP_Constants__id_CellTrafficTrace;
            case DeactivateTrace:
                return Values.NGAP_Constants__id_DeactivateTrace;
            case DownlinkNASTransport:
                return Values.NGAP_Constants__id_DownlinkNASTransport;
            case DownlinkNonUEAssociatedNRPPaTransport:
                return Values.NGAP_Constants__id_DownlinkNonUEAssociatedNRPPaTransport;
            case DownlinkRANConfigurationTransfer:
                return Values.NGAP_Constants__id_DownlinkRANConfigurationTransfer;
            case DownlinkRANStatusTransfer:
                return Values.NGAP_Constants__id_DownlinkRANStatusTransfer;
            case DownlinkUEAssociatedNRPPaTransport:
                return Values.NGAP_Constants__id_DownlinkUEAssociatedNRPPaTransport;
            case ErrorIndication:
                return Values.NGAP_Constants__id_ErrorIndication;
            case HandoverCancelAcknowledge:
            case HandoverCancel:
                return Values.NGAP_Constants__id_HandoverCancel;
            case HandoverCommand:
            case HandoverPreparationFailure:
            case HandoverRequired:
                return Values.NGAP_Constants__id_HandoverPreparation;
            case HandoverFailure:
            case HandoverRequestAcknowledge:
            case HandoverRequest:
                return Values.NGAP_Constants__id_HandoverResourceAllocation;
            case HandoverNotify:
                return Values.NGAP_Constants__id_HandoverNotification;
            case InitialContextSetupFailure:
            case InitialContextSetupResponse:
            case InitialContextSetupRequest:
                return Values.NGAP_Constants__id_InitialContextSetup;
            case InitialUEMessage:
                return Values.NGAP_Constants__id_InitialUEMessage;
            case LocationReportingControl:
                return Values.NGAP_Constants__id_LocationReportingControl;
            case LocationReportingFailureIndication:
                return Values.NGAP_Constants__id_LocationReportingFailureIndication;
            case LocationReport:
                return Values.NGAP_Constants__id_LocationReport;
            case NASNonDeliveryIndication:
                return Values.NGAP_Constants__id_NASNonDeliveryIndication;
            case NGResetAcknowledge:
            case NGReset:
                return Values.NGAP_Constants__id_NGReset;
            case NGSetupFailure:
            case NGSetupRequest:
            case NGSetupResponse:
                return Values.NGAP_Constants__id_NGSetup;
            case OverloadStart:
                return Values.NGAP_Constants__id_OverloadStart;
            case OverloadStop:
                return Values.NGAP_Constants__id_OverloadStop;
            case Paging:
                return Values.NGAP_Constants__id_Paging;
            case PathSwitchRequestAcknowledge:
            case PathSwitchRequestFailure:
            case PathSwitchRequest:
                return Values.NGAP_Constants__id_PathSwitchRequest;
            case PDUSessionResourceModifyConfirm:
            case PDUSessionResourceModifyIndication:
                return Values.NGAP_Constants__id_PDUSessionResourceModifyIndication;
            case PDUSessionResourceModifyRequest:
            case PDUSessionResourceModifyResponse:
                return Values.NGAP_Constants__id_PDUSessionResourceModify;
            case PDUSessionResourceNotify:
                return Values.NGAP_Constants__id_PDUSessionResourceNotify;
            case PDUSessionResourceReleaseCommand:
            case PDUSessionResourceReleaseResponse:
                return Values.NGAP_Constants__id_PDUSessionResourceRelease;
            case PDUSessionResourceSetupRequest:
            case PDUSessionResourceSetupResponse:
                return Values.NGAP_Constants__id_PDUSessionResourceSetup;
            case PrivateMessage:
                return Values.NGAP_Constants__id_PrivateMessage;
            case PWSCancelRequest:
            case PWSCancelResponse:
                return Values.NGAP_Constants__id_PWSCancel;
            case PWSFailureIndication:
                return Values.NGAP_Constants__id_PWSFailureIndication;
            case PWSRestartIndication:
                return Values.NGAP_Constants__id_PWSRestartIndication;
            case RANConfigurationUpdateAcknowledge:
            case RANConfigurationUpdateFailure:
            case RANConfigurationUpdate:
                return Values.NGAP_Constants__id_RANConfigurationUpdate;
            case RerouteNASRequest:
                return Values.NGAP_Constants__id_RerouteNASRequest;
            case RRCInactiveTransitionReport:
                return Values.NGAP_Constants__id_RRCInactiveTransitionReport;
            case TraceFailureIndication:
                return Values.NGAP_Constants__id_TraceFailureIndication;
            case TraceStart:
                return Values.NGAP_Constants__id_TraceStart;
            case UEContextModificationFailure:
            case UEContextModificationRequest:
            case UEContextModificationResponse:
                return Values.NGAP_Constants__id_UEContextModification;
            case UEContextReleaseCommand:
            case UEContextReleaseComplete:
                return Values.NGAP_Constants__id_UEContextRelease;
            case UEContextReleaseRequest:
                return Values.NGAP_Constants__id_UEContextReleaseRequest;
            case UERadioCapabilityCheckRequest:
            case UERadioCapabilityCheckResponse:
                return Values.NGAP_Constants__id_UERadioCapabilityCheck;
            case UERadioCapabilityInfoIndication:
                return Values.NGAP_Constants__id_UERadioCapabilityInfoIndication;
            case UETNLABindingReleaseRequest:
                return Values.NGAP_Constants__id_UETNLABindingRelease;
            case UplinkNASTransport:
                return Values.NGAP_Constants__id_UplinkNASTransport;
            case UplinkNonUEAssociatedNRPPaTransport:
                return Values.NGAP_Constants__id_UplinkNonUEAssociatedNRPPaTransport;
            case UplinkRANConfigurationTransfer:
                return Values.NGAP_Constants__id_UplinkRANConfigurationTransfer;
            case UplinkRANStatusTransfer:
                return Values.NGAP_Constants__id_UplinkRANStatusTransfer;
            case UplinkUEAssociatedNRPPaTransport:
                return Values.NGAP_Constants__id_UplinkUEAssociatedNRPPaTransport;
            case WriteReplaceWarningRequest:
            case WriteReplaceWarningResponse:
                return Values.NGAP_Constants__id_WriteReplaceWarning;
            default:
                Logging.error(Tag.NGAP_INTERNAL, "no procedure code for: %s", this);
                throw new RuntimeException();
        }
    }
}

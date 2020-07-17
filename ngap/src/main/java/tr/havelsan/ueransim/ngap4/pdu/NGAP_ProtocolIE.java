package tr.havelsan.ueransim.ngap4.pdu;

import tr.havelsan.ueransim.ngap2.NgapMessageType;
import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_ProtocolIE extends NGAP_Sequence {

    private final NgapMessageType messageType;

    public NGAP_ProtocolIE_ID id;
    public NGAP_Criticality criticality;
    public NGAP_IEChoice value;

    public NGAP_ProtocolIE(NgapMessageType messageType) {
        this.messageType = messageType;
    }

    private static String messageTypeToAsnName(NgapMessageType type) {
        switch (type) {
            case PDUSessionResourceSetupRequest:
                return "PDUSessionResourceSetupRequestIEs";
            case PDUSessionResourceSetupResponse:
                return "PDUSessionResourceSetupResponseIEs";
            case PDUSessionResourceReleaseCommand:
                return "PDUSessionResourceReleaseCommandIEs";
            case PDUSessionResourceReleaseResponse:
                return "PDUSessionResourceReleaseResponseIEs";
            case PDUSessionResourceModifyRequest:
                return "PDUSessionResourceModifyRequestIEs";
            case PDUSessionResourceModifyResponse:
                return "PDUSessionResourceModifyResponseIEs";
            case PDUSessionResourceNotify:
                return "PDUSessionResourceNotifyIEs";
            case PDUSessionResourceModifyIndication:
                return "PDUSessionResourceModifyIndicationIEs";
            case PDUSessionResourceModifyConfirm:
                return "PDUSessionResourceModifyConfirmIEs";
            case InitialContextSetupRequest:
                return "InitialContextSetupRequestIEs";
            case InitialContextSetupResponse:
                return "InitialContextSetupResponseIEs";
            case InitialContextSetupFailure:
                return "InitialContextSetupFailureIEs";
            case UEContextReleaseRequest:
                return "UEContextReleaseRequest-IEs";
            case UEContextReleaseCommand:
                return "UEContextReleaseCommand-IEs";
            case UEContextReleaseComplete:
                return "UEContextReleaseComplete-IEs";
            case UEContextModificationRequest:
                return "UEContextModificationRequestIEs";
            case UEContextModificationResponse:
                return "UEContextModificationResponseIEs";
            case UEContextModificationFailure:
                return "UEContextModificationFailureIEs";
            case RRCInactiveTransitionReport:
                return "RRCInactiveTransitionReportIEs";
            case HandoverRequired:
                return "HandoverRequiredIEs";
            case HandoverCommand:
                return "HandoverCommandIEs";
            case HandoverPreparationFailure:
                return "HandoverPreparationFailureIEs";
            case HandoverRequest:
                return "HandoverRequestIEs";
            case HandoverRequestAcknowledge:
                return "HandoverRequestAcknowledgeIEs";
            case HandoverFailure:
                return "HandoverFailureIEs";
            case HandoverNotify:
                return "HandoverNotifyIEs";
            case PathSwitchRequest:
                return "PathSwitchRequestIEs";
            case PathSwitchRequestAcknowledge:
                return "PathSwitchRequestAcknowledgeIEs";
            case PathSwitchRequestFailure:
                return "PathSwitchRequestFailureIEs";
            case HandoverCancel:
                return "HandoverCancelIEs";
            case HandoverCancelAcknowledge:
                return "HandoverCancelAcknowledgeIEs";
            case UplinkRANStatusTransfer:
                return "UplinkRANStatusTransferIEs";
            case DownlinkRANStatusTransfer:
                return "DownlinkRANStatusTransferIEs";
            case Paging:
                return "PagingIEs";
            case InitialUEMessage:
                return "InitialUEMessage-IEs";
            case DownlinkNASTransport:
                return "DownlinkNASTransport-IEs";
            case UplinkNASTransport:
                return "UplinkNASTransport-IEs";
            case NASNonDeliveryIndication:
                return "NASNonDeliveryIndication-IEs";
            case RerouteNASRequest:
                return "RerouteNASRequest-IEs";
            case NGSetupRequest:
                return "NGSetupRequestIEs";
            case NGSetupResponse:
                return "NGSetupResponseIEs";
            case NGSetupFailure:
                return "NGSetupFailureIEs";
            case RANConfigurationUpdate:
                return "RANConfigurationUpdateIEs";
            case RANConfigurationUpdateAcknowledge:
                return "RANConfigurationUpdateAcknowledgeIEs";
            case RANConfigurationUpdateFailure:
                return "RANConfigurationUpdateFailureIEs";
            case AMFConfigurationUpdate:
                return "AMFConfigurationUpdateIEs";
            case AMFConfigurationUpdateAcknowledge:
                return "AMFConfigurationUpdateAcknowledgeIEs";
            case AMFConfigurationUpdateFailure:
                return "AMFConfigurationUpdateFailureIEs";
            case AMFStatusIndication:
                return "AMFStatusIndicationIEs";
            case NGReset:
                return "NGResetIEs";
            case NGResetAcknowledge:
                return "NGResetAcknowledgeIEs";
            case ErrorIndication:
                return "ErrorIndicationIEs";
            case OverloadStart:
                return "OverloadStartIEs";
            case OverloadStop:
                return "OverloadStopIEs";
            case UplinkRANConfigurationTransfer:
                return "UplinkRANConfigurationTransferIEs";
            case DownlinkRANConfigurationTransfer:
                return "DownlinkRANConfigurationTransferIEs";
            case WriteReplaceWarningRequest:
                return "WriteReplaceWarningRequestIEs";
            case WriteReplaceWarningResponse:
                return "WriteReplaceWarningResponseIEs";
            case PWSCancelRequest:
                return "PWSCancelRequestIEs";
            case PWSCancelResponse:
                return "PWSCancelResponseIEs";
            case PWSRestartIndication:
                return "PWSRestartIndicationIEs";
            case PWSFailureIndication:
                return "PWSFailureIndicationIEs";
            case DownlinkUEAssociatedNRPPaTransport:
                return "DownlinkUEAssociatedNRPPaTransportIEs";
            case UplinkUEAssociatedNRPPaTransport:
                return "UplinkUEAssociatedNRPPaTransportIEs";
            case DownlinkNonUEAssociatedNRPPaTransport:
                return "DownlinkNonUEAssociatedNRPPaTransportIEs";
            case UplinkNonUEAssociatedNRPPaTransport:
                return "UplinkNonUEAssociatedNRPPaTransportIEs";
            case TraceStart:
                return "TraceStartIEs";
            case TraceFailureIndication:
                return "TraceFailureIndicationIEs";
            case DeactivateTrace:
                return "DeactivateTraceIEs";
            case CellTrafficTrace:
                return "CellTrafficTraceIEs";
            case LocationReportingControl:
                return "LocationReportingControlIEs";
            case LocationReportingFailureIndication:
                return "LocationReportingFailureIndicationIEs";
            case LocationReport:
                return "LocationReportIEs";
            case UETNLABindingReleaseRequest:
                return "UETNLABindingReleaseRequestIEs";
            case UERadioCapabilityInfoIndication:
                return "UERadioCapabilityInfoIndicationIEs";
            case UERadioCapabilityCheckRequest:
                return "UERadioCapabilityCheckRequestIEs";
            case UERadioCapabilityCheckResponse:
                return "UERadioCapabilityCheckResponseIEs";
        }
        throw new RuntimeException();
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"id", "criticality", "value"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"id", "criticality", "value"};
    }

    @Override
    public String getAsnName() {
        return messageTypeToAsnName(messageType);
    }

    @Override
    public String getXmlTagName() {
        return messageTypeToAsnName(messageType);
    }
}

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

    public static String messageTypeToAsnName(NgapMessageType type) {
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

    public static NGAP_ProtocolIE newInstanceFromTag(String tag) {
        switch (tag) {
            case "PDUSessionResourceSetupRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceSetupRequest);
            case "PDUSessionResourceSetupResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceSetupResponse);
            case "PDUSessionResourceReleaseCommandIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceReleaseCommand);
            case "PDUSessionResourceReleaseResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceReleaseResponse);
            case "PDUSessionResourceModifyRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceModifyRequest);
            case "PDUSessionResourceModifyResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceModifyResponse);
            case "PDUSessionResourceNotifyIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceNotify);
            case "PDUSessionResourceModifyIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceModifyIndication);
            case "PDUSessionResourceModifyConfirmIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PDUSessionResourceModifyConfirm);
            case "InitialContextSetupRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.InitialContextSetupRequest);
            case "InitialContextSetupResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.InitialContextSetupResponse);
            case "InitialContextSetupFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.InitialContextSetupFailure);
            case "UEContextReleaseRequest-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextReleaseRequest);
            case "UEContextReleaseCommand-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextReleaseCommand);
            case "UEContextReleaseComplete-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextReleaseComplete);
            case "UEContextModificationRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextModificationRequest);
            case "UEContextModificationResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextModificationResponse);
            case "UEContextModificationFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UEContextModificationFailure);
            case "RRCInactiveTransitionReportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.RRCInactiveTransitionReport);
            case "HandoverRequiredIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverRequired);
            case "HandoverCommandIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverCommand);
            case "HandoverPreparationFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverPreparationFailure);
            case "HandoverRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverRequest);
            case "HandoverRequestAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverRequestAcknowledge);
            case "HandoverFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverFailure);
            case "HandoverNotifyIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverNotify);
            case "PathSwitchRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PathSwitchRequest);
            case "PathSwitchRequestAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PathSwitchRequestAcknowledge);
            case "PathSwitchRequestFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PathSwitchRequestFailure);
            case "HandoverCancelIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverCancel);
            case "HandoverCancelAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.HandoverCancelAcknowledge);
            case "UplinkRANStatusTransferIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UplinkRANStatusTransfer);
            case "DownlinkRANStatusTransferIEs":
                return new NGAP_ProtocolIE(NgapMessageType.DownlinkRANStatusTransfer);
            case "PagingIEs":
                return new NGAP_ProtocolIE(NgapMessageType.Paging);
            case "InitialUEMessage-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.InitialUEMessage);
            case "DownlinkNASTransport-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.DownlinkNASTransport);
            case "UplinkNASTransport-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.UplinkNASTransport);
            case "NASNonDeliveryIndication-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.NASNonDeliveryIndication);
            case "RerouteNASRequest-IEs":
                return new NGAP_ProtocolIE(NgapMessageType.RerouteNASRequest);
            case "NGSetupRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.NGSetupRequest);
            case "NGSetupResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.NGSetupResponse);
            case "NGSetupFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.NGSetupFailure);
            case "RANConfigurationUpdateIEs":
                return new NGAP_ProtocolIE(NgapMessageType.RANConfigurationUpdate);
            case "RANConfigurationUpdateAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.RANConfigurationUpdateAcknowledge);
            case "RANConfigurationUpdateFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.RANConfigurationUpdateFailure);
            case "AMFConfigurationUpdateIEs":
                return new NGAP_ProtocolIE(NgapMessageType.AMFConfigurationUpdate);
            case "AMFConfigurationUpdateAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.AMFConfigurationUpdateAcknowledge);
            case "AMFConfigurationUpdateFailureIEs":
                return new NGAP_ProtocolIE(NgapMessageType.AMFConfigurationUpdateFailure);
            case "AMFStatusIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.AMFStatusIndication);
            case "NGResetIEs":
                return new NGAP_ProtocolIE(NgapMessageType.NGReset);
            case "NGResetAcknowledgeIEs":
                return new NGAP_ProtocolIE(NgapMessageType.NGResetAcknowledge);
            case "ErrorIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.ErrorIndication);
            case "OverloadStartIEs":
                return new NGAP_ProtocolIE(NgapMessageType.OverloadStart);
            case "OverloadStopIEs":
                return new NGAP_ProtocolIE(NgapMessageType.OverloadStop);
            case "UplinkRANConfigurationTransferIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UplinkRANConfigurationTransfer);
            case "DownlinkRANConfigurationTransferIEs":
                return new NGAP_ProtocolIE(NgapMessageType.DownlinkRANConfigurationTransfer);
            case "WriteReplaceWarningRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.WriteReplaceWarningRequest);
            case "WriteReplaceWarningResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.WriteReplaceWarningResponse);
            case "PWSCancelRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PWSCancelRequest);
            case "PWSCancelResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PWSCancelResponse);
            case "PWSRestartIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PWSRestartIndication);
            case "PWSFailureIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.PWSFailureIndication);
            case "DownlinkUEAssociatedNRPPaTransportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.DownlinkUEAssociatedNRPPaTransport);
            case "UplinkUEAssociatedNRPPaTransportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UplinkUEAssociatedNRPPaTransport);
            case "DownlinkNonUEAssociatedNRPPaTransportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.DownlinkNonUEAssociatedNRPPaTransport);
            case "UplinkNonUEAssociatedNRPPaTransportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UplinkNonUEAssociatedNRPPaTransport);
            case "TraceStartIEs":
                return new NGAP_ProtocolIE(NgapMessageType.TraceStart);
            case "TraceFailureIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.TraceFailureIndication);
            case "DeactivateTraceIEs":
                return new NGAP_ProtocolIE(NgapMessageType.DeactivateTrace);
            case "CellTrafficTraceIEs":
                return new NGAP_ProtocolIE(NgapMessageType.CellTrafficTrace);
            case "LocationReportingControlIEs":
                return new NGAP_ProtocolIE(NgapMessageType.LocationReportingControl);
            case "LocationReportingFailureIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.LocationReportingFailureIndication);
            case "LocationReportIEs":
                return new NGAP_ProtocolIE(NgapMessageType.LocationReport);
            case "UETNLABindingReleaseRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UETNLABindingReleaseRequest);
            case "UERadioCapabilityInfoIndicationIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UERadioCapabilityInfoIndication);
            case "UERadioCapabilityCheckRequestIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UERadioCapabilityCheckRequest);
            case "UERadioCapabilityCheckResponseIEs":
                return new NGAP_ProtocolIE(NgapMessageType.UERadioCapabilityCheckResponse);
        }
        return null;
    }
}

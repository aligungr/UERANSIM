package tr.havelsan.ueransim.ngap4.pdu;

import tr.havelsan.ueransim.ngap4.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap4.msg.*;

public class NGAP_MessageChoice extends NGAP_Choice {

    public NGAP_PDUSessionResourceSetupRequest pDUSessionResourceSetupRequest;
    public NGAP_PDUSessionResourceSetupResponse pDUSessionResourceSetupResponse;
    public NGAP_PDUSessionResourceReleaseCommand pDUSessionResourceReleaseCommand;
    public NGAP_PDUSessionResourceReleaseResponse pDUSessionResourceReleaseResponse;
    public NGAP_PDUSessionResourceModifyRequest pDUSessionResourceModifyRequest;
    public NGAP_PDUSessionResourceModifyResponse pDUSessionResourceModifyResponse;
    public NGAP_PDUSessionResourceNotify pDUSessionResourceNotify;
    public NGAP_PDUSessionResourceModifyIndication pDUSessionResourceModifyIndication;
    public NGAP_PDUSessionResourceModifyConfirm pDUSessionResourceModifyConfirm;
    public NGAP_InitialContextSetupRequest initialContextSetupRequest;
    public NGAP_InitialContextSetupResponse initialContextSetupResponse;
    public NGAP_InitialContextSetupFailure initialContextSetupFailure;
    public NGAP_UEContextReleaseRequest uEContextReleaseRequest;
    public NGAP_UEContextReleaseCommand uEContextReleaseCommand;
    public NGAP_UEContextReleaseComplete uEContextReleaseComplete;
    public NGAP_UEContextModificationRequest uEContextModificationRequest;
    public NGAP_UEContextModificationResponse uEContextModificationResponse;
    public NGAP_UEContextModificationFailure uEContextModificationFailure;
    public NGAP_RRCInactiveTransitionReport rRCInactiveTransitionReport;
    public NGAP_HandoverRequired handoverRequired;
    public NGAP_HandoverCommand handoverCommand;
    public NGAP_HandoverPreparationFailure handoverPreparationFailure;
    public NGAP_HandoverRequest handoverRequest;
    public NGAP_HandoverRequestAcknowledge handoverRequestAcknowledge;
    public NGAP_HandoverFailure handoverFailure;
    public NGAP_HandoverNotify handoverNotify;
    public NGAP_PathSwitchRequest pathSwitchRequest;
    public NGAP_PathSwitchRequestAcknowledge pathSwitchRequestAcknowledge;
    public NGAP_PathSwitchRequestFailure pathSwitchRequestFailure;
    public NGAP_HandoverCancel handoverCancel;
    public NGAP_HandoverCancelAcknowledge handoverCancelAcknowledge;
    public NGAP_UplinkRANStatusTransfer uplinkRANStatusTransfer;
    public NGAP_DownlinkRANStatusTransfer downlinkRANStatusTransfer;
    public NGAP_Paging paging;
    public NGAP_InitialUEMessage initialUEMessage;
    public NGAP_DownlinkNASTransport downlinkNASTransport;
    public NGAP_UplinkNASTransport uplinkNASTransport;
    public NGAP_NASNonDeliveryIndication nASNonDeliveryIndication;
    public NGAP_RerouteNASRequest rerouteNASRequest;
    public NGAP_NGSetupRequest nGSetupRequest;
    public NGAP_NGSetupResponse nGSetupResponse;
    public NGAP_NGSetupFailure nGSetupFailure;
    public NGAP_RANConfigurationUpdate rANConfigurationUpdate;
    public NGAP_RANConfigurationUpdateAcknowledge rANConfigurationUpdateAcknowledge;
    public NGAP_RANConfigurationUpdateFailure rANConfigurationUpdateFailure;
    public NGAP_AMFConfigurationUpdate aMFConfigurationUpdate;
    public NGAP_AMFConfigurationUpdateAcknowledge aMFConfigurationUpdateAcknowledge;
    public NGAP_AMFConfigurationUpdateFailure aMFConfigurationUpdateFailure;
    public NGAP_AMFStatusIndication aMFStatusIndication;
    public NGAP_NGReset nGReset;
    public NGAP_NGResetAcknowledge nGResetAcknowledge;
    public NGAP_ErrorIndication errorIndication;
    public NGAP_OverloadStart overloadStart;
    public NGAP_OverloadStop overloadStop;
    public NGAP_UplinkRANConfigurationTransfer uplinkRANConfigurationTransfer;
    public NGAP_DownlinkRANConfigurationTransfer downlinkRANConfigurationTransfer;
    public NGAP_WriteReplaceWarningRequest writeReplaceWarningRequest;
    public NGAP_WriteReplaceWarningResponse writeReplaceWarningResponse;
    public NGAP_PWSCancelRequest pWSCancelRequest;
    public NGAP_PWSCancelResponse pWSCancelResponse;
    public NGAP_PWSRestartIndication pWSRestartIndication;
    public NGAP_PWSFailureIndication pWSFailureIndication;
    public NGAP_DownlinkUEAssociatedNRPPaTransport downlinkUEAssociatedNRPPaTransport;
    public NGAP_UplinkUEAssociatedNRPPaTransport uplinkUEAssociatedNRPPaTransport;
    public NGAP_DownlinkNonUEAssociatedNRPPaTransport downlinkNonUEAssociatedNRPPaTransport;
    public NGAP_UplinkNonUEAssociatedNRPPaTransport uplinkNonUEAssociatedNRPPaTransport;
    public NGAP_TraceStart traceStart;
    public NGAP_TraceFailureIndication traceFailureIndication;
    public NGAP_DeactivateTrace deactivateTrace;
    public NGAP_CellTrafficTrace cellTrafficTrace;
    public NGAP_LocationReportingControl locationReportingControl;
    public NGAP_LocationReportingFailureIndication locationReportingFailureIndication;
    public NGAP_LocationReport locationReport;
    public NGAP_UETNLABindingReleaseRequest uETNLABindingReleaseRequest;
    public NGAP_UERadioCapabilityInfoIndication uERadioCapabilityInfoIndication;
    public NGAP_UERadioCapabilityCheckRequest uERadioCapabilityCheckRequest;
    public NGAP_UERadioCapabilityCheckResponse uERadioCapabilityCheckResponse;
    public NGAP_PrivateMessage privateMessage;

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionResourceSetupRequest", "pDUSessionResourceSetupResponse", "pDUSessionResourceReleaseCommand", "pDUSessionResourceReleaseResponse", "pDUSessionResourceModifyRequest", "pDUSessionResourceModifyResponse", "pDUSessionResourceNotify", "pDUSessionResourceModifyIndication", "pDUSessionResourceModifyConfirm", "initialContextSetupRequest", "initialContextSetupResponse", "initialContextSetupFailure", "uEContextReleaseRequest", "uEContextReleaseCommand", "uEContextReleaseComplete", "uEContextModificationRequest", "uEContextModificationResponse", "uEContextModificationFailure", "rRCInactiveTransitionReport", "handoverRequired", "handoverCommand", "handoverPreparationFailure", "handoverRequest", "handoverRequestAcknowledge", "handoverFailure", "handoverNotify", "pathSwitchRequest", "pathSwitchRequestAcknowledge", "pathSwitchRequestFailure", "handoverCancel", "handoverCancelAcknowledge", "uplinkRANStatusTransfer", "downlinkRANStatusTransfer", "paging", "initialUEMessage", "downlinkNASTransport", "uplinkNASTransport", "nASNonDeliveryIndication", "rerouteNASRequest", "nGSetupRequest", "nGSetupResponse", "nGSetupFailure", "rANConfigurationUpdate", "rANConfigurationUpdateAcknowledge", "rANConfigurationUpdateFailure", "aMFConfigurationUpdate", "aMFConfigurationUpdateAcknowledge", "aMFConfigurationUpdateFailure", "aMFStatusIndication", "nGReset", "nGResetAcknowledge", "errorIndication", "overloadStart", "overloadStop", "uplinkRANConfigurationTransfer", "downlinkRANConfigurationTransfer", "writeReplaceWarningRequest", "writeReplaceWarningResponse", "pWSCancelRequest", "pWSCancelResponse", "pWSRestartIndication", "pWSFailureIndication", "downlinkUEAssociatedNRPPaTransport", "uplinkUEAssociatedNRPPaTransport", "downlinkNonUEAssociatedNRPPaTransport", "uplinkNonUEAssociatedNRPPaTransport", "traceStart", "traceFailureIndication", "deactivateTrace", "cellTrafficTrace", "locationReportingControl", "locationReportingFailureIndication", "locationReport", "uETNLABindingReleaseRequest", "uERadioCapabilityInfoIndication", "uERadioCapabilityCheckRequest", "uERadioCapabilityCheckResponse", "privateMessage"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"PDUSessionResourceSetupRequest", "PDUSessionResourceSetupResponse", "PDUSessionResourceReleaseCommand", "PDUSessionResourceReleaseResponse", "PDUSessionResourceModifyRequest", "PDUSessionResourceModifyResponse", "PDUSessionResourceNotify", "PDUSessionResourceModifyIndication", "PDUSessionResourceModifyConfirm", "InitialContextSetupRequest", "InitialContextSetupResponse", "InitialContextSetupFailure", "UEContextReleaseRequest", "UEContextReleaseCommand", "UEContextReleaseComplete", "UEContextModificationRequest", "UEContextModificationResponse", "UEContextModificationFailure", "RRCInactiveTransitionReport", "HandoverRequired", "HandoverCommand", "HandoverPreparationFailure", "HandoverRequest", "HandoverRequestAcknowledge", "HandoverFailure", "HandoverNotify", "PathSwitchRequest", "PathSwitchRequestAcknowledge", "PathSwitchRequestFailure", "HandoverCancel", "HandoverCancelAcknowledge", "UplinkRANStatusTransfer", "DownlinkRANStatusTransfer", "Paging", "InitialUEMessage", "DownlinkNASTransport", "UplinkNASTransport", "NASNonDeliveryIndication", "RerouteNASRequest", "NGSetupRequest", "NGSetupResponse", "NGSetupFailure", "RANConfigurationUpdate", "RANConfigurationUpdateAcknowledge", "RANConfigurationUpdateFailure", "AMFConfigurationUpdate", "AMFConfigurationUpdateAcknowledge", "AMFConfigurationUpdateFailure", "AMFStatusIndication", "NGReset", "NGResetAcknowledge", "ErrorIndication", "OverloadStart", "OverloadStop", "UplinkRANConfigurationTransfer", "DownlinkRANConfigurationTransfer", "WriteReplaceWarningRequest", "WriteReplaceWarningResponse", "PWSCancelRequest", "PWSCancelResponse", "PWSRestartIndication", "PWSFailureIndication", "DownlinkUEAssociatedNRPPaTransport", "UplinkUEAssociatedNRPPaTransport", "DownlinkNonUEAssociatedNRPPaTransport", "UplinkNonUEAssociatedNRPPaTransport", "TraceStart", "TraceFailureIndication", "DeactivateTrace", "CellTrafficTrace", "LocationReportingControl", "LocationReportingFailureIndication", "LocationReport", "UETNLABindingReleaseRequest", "UERadioCapabilityInfoIndication", "UERadioCapabilityCheckRequest", "UERadioCapabilityCheckResponse", "PrivateMessage"};
    }

    @Override
    public String getAsnName() {
        return "value";
    }

    @Override
    public String getXmlTagName() {
        return "value";
    }
}

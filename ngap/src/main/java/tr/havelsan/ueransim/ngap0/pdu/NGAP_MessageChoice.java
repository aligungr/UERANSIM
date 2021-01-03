/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.pdu;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.msg.*;

public class NGAP_MessageChoice extends NGAP_Choice {

    public NGAP_AMFConfigurationUpdate AMFConfigurationUpdate;
    public NGAP_AMFConfigurationUpdateAcknowledge AMFConfigurationUpdateAcknowledge;
    public NGAP_AMFConfigurationUpdateFailure AMFConfigurationUpdateFailure;
    public NGAP_AMFStatusIndication AMFStatusIndication;
    public NGAP_CellTrafficTrace CellTrafficTrace;
    public NGAP_DeactivateTrace DeactivateTrace;
    public NGAP_DownlinkNASTransport DownlinkNASTransport;
    public NGAP_DownlinkNonUEAssociatedNRPPaTransport DownlinkNonUEAssociatedNRPPaTransport;
    public NGAP_DownlinkRANConfigurationTransfer DownlinkRANConfigurationTransfer;
    public NGAP_DownlinkRANStatusTransfer DownlinkRANStatusTransfer;
    public NGAP_DownlinkUEAssociatedNRPPaTransport DownlinkUEAssociatedNRPPaTransport;
    public NGAP_ErrorIndication ErrorIndication;
    public NGAP_HandoverCancel HandoverCancel;
    public NGAP_HandoverCancelAcknowledge HandoverCancelAcknowledge;
    public NGAP_HandoverCommand HandoverCommand;
    public NGAP_HandoverFailure HandoverFailure;
    public NGAP_HandoverNotify HandoverNotify;
    public NGAP_HandoverPreparationFailure HandoverPreparationFailure;
    public NGAP_HandoverRequest HandoverRequest;
    public NGAP_HandoverRequestAcknowledge HandoverRequestAcknowledge;
    public NGAP_HandoverRequired HandoverRequired;
    public NGAP_InitialContextSetupFailure InitialContextSetupFailure;
    public NGAP_InitialContextSetupRequest InitialContextSetupRequest;
    public NGAP_InitialContextSetupResponse InitialContextSetupResponse;
    public NGAP_InitialUEMessage InitialUEMessage;
    public NGAP_LocationReport LocationReport;
    public NGAP_LocationReportingControl LocationReportingControl;
    public NGAP_LocationReportingFailureIndication LocationReportingFailureIndication;
    public NGAP_NASNonDeliveryIndication NASNonDeliveryIndication;
    public NGAP_NGReset NGReset;
    public NGAP_NGResetAcknowledge NGResetAcknowledge;
    public NGAP_NGSetupFailure NGSetupFailure;
    public NGAP_NGSetupRequest NGSetupRequest;
    public NGAP_NGSetupResponse NGSetupResponse;
    public NGAP_OverloadStart OverloadStart;
    public NGAP_OverloadStop OverloadStop;
    public NGAP_Paging Paging;
    public NGAP_PathSwitchRequest PathSwitchRequest;
    public NGAP_PathSwitchRequestAcknowledge PathSwitchRequestAcknowledge;
    public NGAP_PathSwitchRequestFailure PathSwitchRequestFailure;
    public NGAP_PDUSessionResourceModifyConfirm PDUSessionResourceModifyConfirm;
    public NGAP_PDUSessionResourceModifyIndication PDUSessionResourceModifyIndication;
    public NGAP_PDUSessionResourceModifyRequest PDUSessionResourceModifyRequest;
    public NGAP_PDUSessionResourceModifyResponse PDUSessionResourceModifyResponse;
    public NGAP_PDUSessionResourceNotify PDUSessionResourceNotify;
    public NGAP_PDUSessionResourceReleaseCommand PDUSessionResourceReleaseCommand;
    public NGAP_PDUSessionResourceReleaseResponse PDUSessionResourceReleaseResponse;
    public NGAP_PDUSessionResourceSetupRequest PDUSessionResourceSetupRequest;
    public NGAP_PDUSessionResourceSetupResponse PDUSessionResourceSetupResponse;
    public NGAP_PrivateMessage PrivateMessage;
    public NGAP_PWSCancelRequest PWSCancelRequest;
    public NGAP_PWSCancelResponse PWSCancelResponse;
    public NGAP_PWSFailureIndication PWSFailureIndication;
    public NGAP_PWSRestartIndication PWSRestartIndication;
    public NGAP_RANConfigurationUpdate RANConfigurationUpdate;
    public NGAP_RANConfigurationUpdateAcknowledge RANConfigurationUpdateAcknowledge;
    public NGAP_RANConfigurationUpdateFailure RANConfigurationUpdateFailure;
    public NGAP_RerouteNASRequest RerouteNASRequest;
    public NGAP_RRCInactiveTransitionReport RRCInactiveTransitionReport;
    public NGAP_SecondaryRATDataUsageReport SecondaryRATDataUsageReport;
    public NGAP_TraceFailureIndication TraceFailureIndication;
    public NGAP_TraceStart TraceStart;
    public NGAP_UEContextModificationFailure UEContextModificationFailure;
    public NGAP_UEContextModificationRequest UEContextModificationRequest;
    public NGAP_UEContextModificationResponse UEContextModificationResponse;
    public NGAP_UEContextReleaseCommand UEContextReleaseCommand;
    public NGAP_UEContextReleaseComplete UEContextReleaseComplete;
    public NGAP_UEContextReleaseRequest UEContextReleaseRequest;
    public NGAP_UERadioCapabilityCheckRequest UERadioCapabilityCheckRequest;
    public NGAP_UERadioCapabilityCheckResponse UERadioCapabilityCheckResponse;
    public NGAP_UERadioCapabilityInfoIndication UERadioCapabilityInfoIndication;
    public NGAP_UETNLABindingReleaseRequest UETNLABindingReleaseRequest;
    public NGAP_UplinkNASTransport UplinkNASTransport;
    public NGAP_UplinkNonUEAssociatedNRPPaTransport UplinkNonUEAssociatedNRPPaTransport;
    public NGAP_UplinkRANConfigurationTransfer UplinkRANConfigurationTransfer;
    public NGAP_UplinkRANStatusTransfer UplinkRANStatusTransfer;
    public NGAP_UplinkUEAssociatedNRPPaTransport UplinkUEAssociatedNRPPaTransport;
    public NGAP_WriteReplaceWarningRequest WriteReplaceWarningRequest;
    public NGAP_WriteReplaceWarningResponse WriteReplaceWarningResponse;

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"AMFConfigurationUpdate", "AMFConfigurationUpdateAcknowledge", "AMFConfigurationUpdateFailure", "AMFStatusIndication", "CellTrafficTrace", "DeactivateTrace", "DownlinkNASTransport", "DownlinkNonUEAssociatedNRPPaTransport", "DownlinkRANConfigurationTransfer", "DownlinkRANStatusTransfer", "DownlinkUEAssociatedNRPPaTransport", "ErrorIndication", "HandoverCancel", "HandoverCancelAcknowledge", "HandoverCommand", "HandoverFailure", "HandoverNotify", "HandoverPreparationFailure", "HandoverRequest", "HandoverRequestAcknowledge", "HandoverRequired", "InitialContextSetupFailure", "InitialContextSetupRequest", "InitialContextSetupResponse", "InitialUEMessage", "LocationReport", "LocationReportingControl", "LocationReportingFailureIndication", "NASNonDeliveryIndication", "NGReset", "NGResetAcknowledge", "NGSetupFailure", "NGSetupRequest", "NGSetupResponse", "OverloadStart", "OverloadStop", "Paging", "PathSwitchRequest", "PathSwitchRequestAcknowledge", "PathSwitchRequestFailure", "PDUSessionResourceModifyConfirm", "PDUSessionResourceModifyIndication", "PDUSessionResourceModifyRequest", "PDUSessionResourceModifyResponse", "PDUSessionResourceNotify", "PDUSessionResourceReleaseCommand", "PDUSessionResourceReleaseResponse", "PDUSessionResourceSetupRequest", "PDUSessionResourceSetupResponse", "PrivateMessage", "PWSCancelRequest", "PWSCancelResponse", "PWSFailureIndication", "PWSRestartIndication", "RANConfigurationUpdate", "RANConfigurationUpdateAcknowledge", "RANConfigurationUpdateFailure", "RerouteNASRequest", "RRCInactiveTransitionReport", "SecondaryRATDataUsageReport", "TraceFailureIndication", "TraceStart", "UEContextModificationFailure", "UEContextModificationRequest", "UEContextModificationResponse", "UEContextReleaseCommand", "UEContextReleaseComplete", "UEContextReleaseRequest", "UERadioCapabilityCheckRequest", "UERadioCapabilityCheckResponse", "UERadioCapabilityInfoIndication", "UETNLABindingReleaseRequest", "UplinkNASTransport", "UplinkNonUEAssociatedNRPPaTransport", "UplinkRANConfigurationTransfer", "UplinkRANStatusTransfer", "UplinkUEAssociatedNRPPaTransport", "WriteReplaceWarningRequest", "WriteReplaceWarningResponse"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"AMFConfigurationUpdate", "AMFConfigurationUpdateAcknowledge", "AMFConfigurationUpdateFailure", "AMFStatusIndication", "CellTrafficTrace", "DeactivateTrace", "DownlinkNASTransport", "DownlinkNonUEAssociatedNRPPaTransport", "DownlinkRANConfigurationTransfer", "DownlinkRANStatusTransfer", "DownlinkUEAssociatedNRPPaTransport", "ErrorIndication", "HandoverCancel", "HandoverCancelAcknowledge", "HandoverCommand", "HandoverFailure", "HandoverNotify", "HandoverPreparationFailure", "HandoverRequest", "HandoverRequestAcknowledge", "HandoverRequired", "InitialContextSetupFailure", "InitialContextSetupRequest", "InitialContextSetupResponse", "InitialUEMessage", "LocationReport", "LocationReportingControl", "LocationReportingFailureIndication", "NASNonDeliveryIndication", "NGReset", "NGResetAcknowledge", "NGSetupFailure", "NGSetupRequest", "NGSetupResponse", "OverloadStart", "OverloadStop", "Paging", "PathSwitchRequest", "PathSwitchRequestAcknowledge", "PathSwitchRequestFailure", "PDUSessionResourceModifyConfirm", "PDUSessionResourceModifyIndication", "PDUSessionResourceModifyRequest", "PDUSessionResourceModifyResponse", "PDUSessionResourceNotify", "PDUSessionResourceReleaseCommand", "PDUSessionResourceReleaseResponse", "PDUSessionResourceSetupRequest", "PDUSessionResourceSetupResponse", "PrivateMessage", "PWSCancelRequest", "PWSCancelResponse", "PWSFailureIndication", "PWSRestartIndication", "RANConfigurationUpdate", "RANConfigurationUpdateAcknowledge", "RANConfigurationUpdateFailure", "RerouteNASRequest", "RRCInactiveTransitionReport", "SecondaryRATDataUsageReport", "TraceFailureIndication", "TraceStart", "UEContextModificationFailure", "UEContextModificationRequest", "UEContextModificationResponse", "UEContextReleaseCommand", "UEContextReleaseComplete", "UEContextReleaseRequest", "UERadioCapabilityCheckRequest", "UERadioCapabilityCheckResponse", "UERadioCapabilityInfoIndication", "UETNLABindingReleaseRequest", "UplinkNASTransport", "UplinkNonUEAssociatedNRPPaTransport", "UplinkRANConfigurationTransfer", "UplinkRANStatusTransfer", "UplinkUEAssociatedNRPPaTransport", "WriteReplaceWarningRequest", "WriteReplaceWarningResponse"};
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException();
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException();
    }
}

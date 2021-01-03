/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0;

public enum NgapProtocolIeType {
    PDUSessionResourceModifyRequestTransferIEs,
    PDUSessionResourceSetupRequestTransferIEs,
    PDUSessionResourceSetupRequestIEs,
    PDUSessionResourceSetupResponseIEs,
    PDUSessionResourceReleaseCommandIEs,
    PDUSessionResourceReleaseResponseIEs,
    PDUSessionResourceModifyRequestIEs,
    PDUSessionResourceModifyResponseIEs,
    PDUSessionResourceNotifyIEs,
    PDUSessionResourceModifyIndicationIEs,
    PDUSessionResourceModifyConfirmIEs,
    InitialContextSetupRequestIEs,
    InitialContextSetupResponseIEs,
    InitialContextSetupFailureIEs,
    UEContextReleaseRequest_IEs,
    UEContextReleaseCommand_IEs,
    UEContextReleaseComplete_IEs,
    UEContextModificationRequestIEs,
    UEContextModificationResponseIEs,
    UEContextModificationFailureIEs,
    RRCInactiveTransitionReportIEs,
    HandoverRequiredIEs,
    HandoverCommandIEs,
    HandoverPreparationFailureIEs,
    HandoverRequestIEs,
    HandoverRequestAcknowledgeIEs,
    HandoverFailureIEs,
    HandoverNotifyIEs,
    PathSwitchRequestIEs,
    PathSwitchRequestAcknowledgeIEs,
    PathSwitchRequestFailureIEs,
    HandoverCancelIEs,
    HandoverCancelAcknowledgeIEs,
    UplinkRANStatusTransferIEs,
    DownlinkRANStatusTransferIEs,
    PagingIEs,
    InitialUEMessage_IEs,
    DownlinkNASTransport_IEs,
    UplinkNASTransport_IEs,
    NASNonDeliveryIndication_IEs,
    RerouteNASRequest_IEs,
    NGSetupRequestIEs,
    NGSetupResponseIEs,
    NGSetupFailureIEs,
    RANConfigurationUpdateIEs,
    RANConfigurationUpdateAcknowledgeIEs,
    RANConfigurationUpdateFailureIEs,
    AMFConfigurationUpdateIEs,
    AMFConfigurationUpdateAcknowledgeIEs,
    AMFConfigurationUpdateFailureIEs,
    AMFStatusIndicationIEs,
    NGResetIEs,
    NGResetAcknowledgeIEs,
    ErrorIndicationIEs,
    OverloadStartIEs,
    OverloadStopIEs,
    UplinkRANConfigurationTransferIEs,
    DownlinkRANConfigurationTransferIEs,
    WriteReplaceWarningRequestIEs,
    WriteReplaceWarningResponseIEs,
    PWSCancelRequestIEs,
    PWSCancelResponseIEs,
    PWSRestartIndicationIEs,
    PWSFailureIndicationIEs,
    DownlinkUEAssociatedNRPPaTransportIEs,
    UplinkUEAssociatedNRPPaTransportIEs,
    DownlinkNonUEAssociatedNRPPaTransportIEs,
    UplinkNonUEAssociatedNRPPaTransportIEs,
    TraceStartIEs,
    TraceFailureIndicationIEs,
    DeactivateTraceIEs,
    CellTrafficTraceIEs,
    LocationReportingControlIEs,
    LocationReportingFailureIndicationIEs,
    LocationReportIEs,
    UETNLABindingReleaseRequestIEs,
    UERadioCapabilityInfoIndicationIEs,
    UERadioCapabilityCheckRequestIEs,
    UERadioCapabilityCheckResponseIEs,
    SecondaryRATDataUsageReportIEs;

    public String getAsnName() {
        var name = this.name();
        if (name.endsWith("_IEs"))
            return name.substring(0, name.length() - "_IEs".length()) + "-IEs";
        return name;
    }

    public static NgapProtocolIeType fromAsnName(String name) {
        if (name.endsWith("-IEs"))
            name = name.substring(0, name.length() - "-IEs".length()) + "_IEs";
        for (var item : NgapProtocolIeType.values()) {
            if (item.name().equals(name))
                return item;
        }
        return null;
    }

    public static NgapProtocolIeType forMessage(String message) {
        for (var item : NgapProtocolIeType.values()) {
            if (item.name().equals(message + "IEs"))
                return item;
            if (item.name().equals(message + "_IEs"))
                return item;
        }
        return null;
    }
}

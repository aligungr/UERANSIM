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
 */

package tr.havelsan.ueransim.ngap0;

import tr.havelsan.ueransim.ngap0.core.NGAP_Value;

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
    UERadioCapabilityCheckResponseIEs;

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

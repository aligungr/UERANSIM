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

import fr.marben.asnsdk.japi.spe.Value;

import java.util.Comparator;
import java.util.List;

// TODO: Optimize this
class ProtocolIeOrdering {

    /**
     * This method sorts the protocol information elements according to order specified in ASN.1
     * Also removes any protocol information element which is not exist in the ASN.1 description of relevant procedure
     */
    public static void processProtocolIEs(List<NgapBuilder.ProtocolIE> protocolIEs, Value procedureContent) {
        var procedureClass = procedureContent.getClass();
        var list = ProtocolIeOrdering.findProtocolIeOrdering(procedureClass);
        protocolIEs.sort(Comparator.comparingInt(ie -> list.indexOf(ie.value.getClass())));

        // remove procolIe if not included in list
        protocolIEs.removeIf(ie -> !list.contains(ie.value.getClass()));
    }

    private static List<Class<?>> findProtocolIeOrdering(Class<?> procedureClass) {
        var order = new Class<?>[0];

        if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNonUEAssociatedNRPPaTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RoutingID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NRPPa_PDU.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverRequired.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.HandoverType.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TargetID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.DirectForwardingPathAvailability.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceListHORqd.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SourceToTarget_TransparentContainer.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.RANConfigurationUpdate.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RANNodeName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SupportedTAList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkRANConfigurationTransfer.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.SONConfigurationTransfer.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverCancelAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverCommand.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.HandoverType.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NASSecurityParametersFromNGRAN.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceHandoverList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceToReleaseListHOCmd.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TargetToSource_TransparentContainer.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.RANConfigurationUpdateAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.GlobalRANNodeID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANNodeName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SupportedTAList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.ServedGUAMIList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RelativeAMFCapacity.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PLMNSupportList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TimeToWait.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkUEAssociatedNRPPaTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RoutingID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NRPPa_PDU.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceModifyRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANPagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceModifyListModReq.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANPagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.MobilityRestrictionList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.IndexToRFSP.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEAggregateMaximumBitRate.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkUEAssociatedNRPPaTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RoutingID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NRPPa_PDU.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceReleaseCommand.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANPagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceToReleaseListRelCmd.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseComplete.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.InfoOnRecommendedCellsAndRANNodesForPaging.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceListCxtRelCpl.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.AMFStatusIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.UnavailableGUAMIList.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGResetAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.UE_associatedLogicalNG_connectionList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverPreparationFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PWSCancelResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.MessageIdentifier.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SerialNumber.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.BroadcastCancelledAreaList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.LocationReportingFailureIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.OverloadStart.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.OverloadResponse.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TrafficLoadReductionIndication.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.OverloadStartNSSAIList.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UERadioCapabilityInfoIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapability.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapabilityForPaging.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceSetupResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSetupListSURes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToSetupListSURes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToSetupListCxtFail.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.RerouteNASRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGAP_Message.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFSetID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceNotify.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceNotifyList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListNot.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverCancel.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.AMFConfigurationUpdateFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TimeToWait.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextModificationFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PrivateMessage.class) {
            order = new Class[]{

            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PathSwitchRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UESecurityCapabilities.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceToBeSwitchedDLList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToSetupListPSReq.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextModificationRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANPagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SecurityKey.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.IndexToRFSP.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEAggregateMaximumBitRate.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UESecurityCapabilities.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CoreNetworkAssistanceInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.EmergencyFallbackIndicator.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCInactiveTransitionReportRequest.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceModifyConfirm.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceModifyListModCfm.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToModifyListModCfm.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSetupListCxtRes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToSetupListCxtRes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextModificationResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCState.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkRANConfigurationTransfer.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.SONConfigurationTransfer.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceReleaseResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListRelRes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.RANConfigurationUpdateFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TimeToWait.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.AMFConfigurationUpdate.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.ServedGUAMIList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RelativeAMFCapacity.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PLMNSupportList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_TNLAssociationToAddList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_TNLAssociationToRemoveList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_TNLAssociationToUpdateList.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.TraceFailureIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NGRANTraceID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverNotify.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.WriteReplaceWarningResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.MessageIdentifier.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SerialNumber.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.BroadcastCompletedAreaList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.WriteReplaceWarningRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.MessageIdentifier.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SerialNumber.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningAreaList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RepetitionPeriod.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NumberOfBroadcastsRequested.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningType.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningSecurityInfo.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.DataCodingScheme.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningMessageContents.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.ConcurrentWarningMessageInd.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningAreaCoordinates.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.RRCInactiveTransitionReport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCState.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.HandoverType.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEAggregateMaximumBitRate.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CoreNetworkAssistanceInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UESecurityCapabilities.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SecurityContext.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NewSecurityContextInd.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSetupListHOReq.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TraceActivation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.MaskedIMEISV.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SourceToTarget_TransparentContainer.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.MobilityRestrictionList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.LocationReportingRequestType.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCInactiveTransitionReportRequest.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.GUAMI.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverRequestAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceAdmittedList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToSetupListHOAck.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TargetToSource_TransparentContainer.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.HandoverFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DeactivateTrace.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NGRANTraceID.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkNonUEAssociatedNRPPaTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RoutingID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NRPPa_PDU.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.CellTrafficTrace.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NGRANTraceID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NGRAN_CGI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TransportLayerAddress.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkNASTransport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceListCxtRelReq.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceSetupRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANPagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSetupListSUReq.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PWSFailureIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.PWSFailedCellIDList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.GlobalRANNodeID.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.LocationReportingControl.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.LocationReportingRequestType.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceModifyIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceModifyListModInd.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGReset.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.ResetType.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PathSwitchRequestFailure.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListPSFail.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.ErrorIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UERadioCapabilityCheckRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapability.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UplinkRANStatusTransfer.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANStatusTransfer_TransparentContainer.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFName.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEAggregateMaximumBitRate.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CoreNetworkAssistanceInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.GUAMI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSetupListCxtReq.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UESecurityCapabilities.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SecurityKey.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TraceActivation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.MobilityRestrictionList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapability.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.IndexToRFSP.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.MaskedIMEISV.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.EmergencyFallbackIndicator.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCInactiveTransitionReportRequest.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapabilityForPaging.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkRANStatusTransfer.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RANStatusTransfer_TransparentContainer.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PathSwitchRequestAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UESecurityCapabilities.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SecurityContext.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NewSecurityContextInd.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceSwitchedList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceReleasedListPSAck.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CoreNetworkAssistanceInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCInactiveTransitionReportRequest.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.LocationReport.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEPresenceInAreaOfInterestList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.LocationReportingRequestType.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PWSRestartIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.CellIDListForRestart.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.GlobalRANNodeID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TAIListForRestart.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.EmergencyAreaIDListForRestart.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PWSCancelRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.MessageIdentifier.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.SerialNumber.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.WarningAreaList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CancelAllWarningMessages.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.Paging.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.UEPagingIdentity.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TAIListForPaging.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PagingPriority.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UERadioCapabilityForPaging.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PagingOrigin.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AssistanceDataForPaging.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UETNLABindingReleaseRequest.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.UE_NGAP_IDs.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.OverloadStop.class) {
            order = new Class[]{

            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.UERadioCapabilityCheckResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.IMSVoiceSupportIndicator.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.NASNonDeliveryIndication.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.Cause.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.TraceStart.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TraceActivation.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialUEMessage.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.FiveG_S_TMSI.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AMFSetID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UEContextRequest.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.AllowedNSSAI.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceModifyResponse.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceModifyListModRes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.PDUSessionResourceFailedToModifyListModRes.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        } else if (procedureClass == tr.havelsan.ueransim.ngap.ngap_pdu_contents.AMFConfigurationUpdateAcknowledge.class) {
            order = new Class[]{
                    tr.havelsan.ueransim.ngap.ngap_ies.AMF_TNLAssociationSetupList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.TNLAssociationList.class,
                    tr.havelsan.ueransim.ngap.ngap_ies.CriticalityDiagnostics.class,
            };
        }

        return List.of(order);
    }
}

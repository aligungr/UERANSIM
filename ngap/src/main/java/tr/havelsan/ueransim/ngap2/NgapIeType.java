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

public enum NgapIeType {
    AllowedNSSAI("AllowedNSSAI"),
    AMF_TNLAssociationSetupList("AMF-TNLAssociationSetupList"),
    AMF_TNLAssociationToAddList("AMF-TNLAssociationToAddList"),
    AMF_TNLAssociationToRemoveList("AMF-TNLAssociationToRemoveList"),
    AMF_TNLAssociationToUpdateList("AMF-TNLAssociationToUpdateList"),
    AMF_UE_NGAP_ID("AMF-UE-NGAP-ID"),
    AMFName("AMFName"),
    AMFSetID("AMFSetID"),
    AssistanceDataForPaging("AssistanceDataForPaging"),
    BroadcastCancelledAreaList("BroadcastCancelledAreaList"),
    BroadcastCompletedAreaList("BroadcastCompletedAreaList"),
    CancelAllWarningMessages("CancelAllWarningMessages"),
    Cause("Cause"),
    CellIDListForRestart("CellIDListForRestart"),
    ConcurrentWarningMessageInd("ConcurrentWarningMessageInd"),
    CoreNetworkAssistanceInformation("CoreNetworkAssistanceInformation"),
    CriticalityDiagnostics("CriticalityDiagnostics"),
    DataCodingScheme("DataCodingScheme"),
    DirectForwardingPathAvailability("DirectForwardingPathAvailability"),
    EmergencyAreaIDListForRestart("EmergencyAreaIDListForRestart"),
    EmergencyFallbackIndicator("EmergencyFallbackIndicator"),
    FiveG_S_TMSI("FiveG-S-TMSI"),
    GlobalRANNodeID("GlobalRANNodeID"),
    GUAMI("GUAMI"),
    HandoverType("HandoverType"),
    IMSVoiceSupportIndicator("IMSVoiceSupportIndicator"),
    IndexToRFSP("IndexToRFSP"),
    InfoOnRecommendedCellsAndRANNodesForPaging("InfoOnRecommendedCellsAndRANNodesForPaging"),
    LocationReportingRequestType("LocationReportingRequestType"),
    MaskedIMEISV("MaskedIMEISV"),
    MessageIdentifier("MessageIdentifier"),
    MobilityRestrictionList("MobilityRestrictionList"),
    NAS_PDU("NAS-PDU"),
    NASSecurityParametersFromNGRAN("NASSecurityParametersFromNGRAN"),
    NewSecurityContextInd("NewSecurityContextInd"),
    NGAP_Message("NGAP-Message"),
    NGRAN_CGI("NGRAN-CGI"),
    NGRANTraceID("NGRANTraceID"),
    NRPPa_PDU("NRPPa-PDU"),
    NumberOfBroadcastsRequested("NumberOfBroadcastsRequested"),
    OverloadStartNSSAIList("OverloadStartNSSAIList"),
    PagingDRX("PagingDRX"),
    PagingOrigin("PagingOrigin"),
    PagingPriority("PagingPriority"),
    PDUSessionResourceAdmittedList("PDUSessionResourceAdmittedList"),
    PDUSessionResourceFailedToModifyListModCfm("PDUSessionResourceFailedToModifyListModCfm"),
    PDUSessionResourceFailedToModifyListModRes("PDUSessionResourceFailedToModifyListModRes"),
    PDUSessionResourceFailedToSetupListCxtFail("PDUSessionResourceFailedToSetupListCxtFail"),
    PDUSessionResourceFailedToSetupListCxtRes("PDUSessionResourceFailedToSetupListCxtRes"),
    PDUSessionResourceFailedToSetupListHOAck("PDUSessionResourceFailedToSetupListHOAck"),
    PDUSessionResourceFailedToSetupListPSReq("PDUSessionResourceFailedToSetupListPSReq"),
    PDUSessionResourceFailedToSetupListSURes("PDUSessionResourceFailedToSetupListSURes"),
    PDUSessionResourceHandoverList("PDUSessionResourceHandoverList"),
    PDUSessionResourceListCxtRelCpl("PDUSessionResourceListCxtRelCpl"),
    PDUSessionResourceListCxtRelReq("PDUSessionResourceListCxtRelReq"),
    PDUSessionResourceListHORqd("PDUSessionResourceListHORqd"),
    PDUSessionResourceModifyListModCfm("PDUSessionResourceModifyListModCfm"),
    PDUSessionResourceModifyListModInd("PDUSessionResourceModifyListModInd"),
    PDUSessionResourceModifyListModReq("PDUSessionResourceModifyListModReq"),
    PDUSessionResourceModifyListModRes("PDUSessionResourceModifyListModRes"),
    PDUSessionResourceNotifyList("PDUSessionResourceNotifyList"),
    PDUSessionResourceReleasedListNot("PDUSessionResourceReleasedListNot"),
    PDUSessionResourceReleasedListPSAck("PDUSessionResourceReleasedListPSAck"),
    PDUSessionResourceReleasedListPSFail("PDUSessionResourceReleasedListPSFail"),
    PDUSessionResourceReleasedListRelRes("PDUSessionResourceReleasedListRelRes"),
    PDUSessionResourceSetupListCxtReq("PDUSessionResourceSetupListCxtReq"),
    PDUSessionResourceSetupListCxtRes("PDUSessionResourceSetupListCxtRes"),
    PDUSessionResourceSetupListHOReq("PDUSessionResourceSetupListHOReq"),
    PDUSessionResourceSetupListSUReq("PDUSessionResourceSetupListSUReq"),
    PDUSessionResourceSetupListSURes("PDUSessionResourceSetupListSURes"),
    PDUSessionResourceSwitchedList("PDUSessionResourceSwitchedList"),
    PDUSessionResourceToBeSwitchedDLList("PDUSessionResourceToBeSwitchedDLList"),
    PDUSessionResourceToReleaseListHOCmd("PDUSessionResourceToReleaseListHOCmd"),
    PDUSessionResourceToReleaseListRelCm("PDUSessionResourceToReleaseListRelCm"),
    PLMNSupportList("PLMNSupportList"),
    PWSFailedCellIDList("PWSFailedCellIDList"),
    RAN_UE_NGAP_ID("RAN-UE-NGAP-ID"),
    RANNodeName("RANNodeName"),
    RANPagingPriority("RANPagingPriority"),
    RANStatusTransfer_TransparentContainer("RANStatusTransfer-TransparentContainer"),
    RelativeAMFCapacity("RelativeAMFCapacity"),
    RepetitionPeriod("RepetitionPeriod"),
    ResetType("ResetType"),
    RoutingID("RoutingID"),
    RRCEstablishmentCause("RRCEstablishmentCause"),
    RRCInactiveTransitionReportRequest("RRCInactiveTransitionReportRequest"),
    RRCState("RRCState"),
    SecurityContext("SecurityContext"),
    SecurityKey("SecurityKey"),
    SerialNumber("SerialNumber"),
    ServedGUAMIList("ServedGUAMIList"),
    SONConfigurationTransfer("SONConfigurationTransfer"),
    SourceToTarget_TransparentContainer("SourceToTarget-TransparentContainer"),
    SupportedTAList("SupportedTAList"),
    TAIListForPaging("TAIListForPaging"),
    TAIListForRestart("TAIListForRestart"),
    TargetID("TargetID"),
    TargetToSource_TransparentContainer("TargetToSource-TransparentContainer"),
    TimeToWait("TimeToWait"),
    TNLAssociationList("TNLAssociationList"),
    TraceActivation("TraceActivation"),
    TrafficLoadReductionIndication("TrafficLoadReductionIndication"),
    TransportLayerAddress("TransportLayerAddress"),
    UE_associatedLogicalNG_connectionList("UE-associatedLogicalNG-connectionList"),
    UE_NGAP_IDs("UE-NGAP-IDs"),
    UEAggregateMaximumBitRate("UEAggregateMaximumBitRate"),
    UEContextRequest("UEContextRequest"),
    UEPagingIdentity("UEPagingIdentity"),
    UEPresenceInAreaOfInterestList("UEPresenceInAreaOfInterestList"),
    UERadioCapability("UERadioCapability"),
    UERadioCapabilityForPaging("UERadioCapabilityForPaging"),
    UESecurityCapabilities("UESecurityCapabilities"),
    UnavailableGUAMIList("UnavailableGUAMIList"),
    UserLocationInformation("UserLocationInformation"),
    WarningAreaCoordinates("WarningAreaCoordinates"),
    WarningAreaList("WarningAreaList"),
    WarningMessageContents("WarningMessageContents"),
    WarningSecurityInfo("WarningSecurityInfo"),
    WarningType("WarningType"),
    OverloadResponse("OverloadResponse");

    public final String typeName;

    NgapIeType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}

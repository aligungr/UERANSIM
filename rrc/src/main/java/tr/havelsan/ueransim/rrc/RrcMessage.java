/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RrcMessage {

    // RRC_BCCH_BCH_Message
    // RRC messages that may be sent from the network to the UE via BCH on the BCCH logical channel
    public RRC_MIB mib;

    // RRC_BCCH_DL_SCH_Message
    // RRC messages that may be sent from the network to the UE via DL-SCH on the BCCH logical channel.
    public RRC_SystemInformation systemInformation;
    public RRC_SIB1 systemInformationBlockType1;

    // RRC_DL_CCCH_Message
    // RRC messages that may be sent from the Network to the UE on the downlink CCCH logical channel.
    public RRC_RRCReject rrcReject;
    public RRC_RRCSetup rrcSetup;

    // RRC_DL_DCCH_Message
    // RRC messages that may be sent from the network to the UE on the downlink DCCH logical channel.
    public RRC_RRCReconfiguration rrcReconfiguration;
    public RRC_RRCResume rrcResume;
    public RRC_RRCRelease rrcRelease;
    public RRC_RRCReestablishment rrcReestablishment;
    public RRC_SecurityModeCommand securityModeCommand;
    public RRC_DLInformationTransfer dlInformationTransfer;
    public RRC_UECapabilityEnquiry ueCapabilityEnquiry;
    public RRC_CounterCheck counterCheck;
    public RRC_MobilityFromNRCommand mobilityFromNRCommand;

    // RRC_PCCH_Message
    // RRC messages that may be sent from the Network to the UE on the PCCH logical channel.
    public RRC_Paging paging;

    // RRC_UL_CCCH_Message
    // 48bit RRC messages that may be sent from the UE to the Network on the uplink CCCH logical channel.
    public RRC_RRCSetupRequest rrcSetupRequest;
    public RRC_RRCResumeRequest rrcResumeRequest;
    public RRC_RRCReestablishmentRequest rrcReestablishmentRequest;
    public RRC_RRCSystemInfoRequest rrcSystemInfoRequest;

    // RRC_UL_CCCH1_Message
    // 64bit RRC messages that may be sent from the UE to the Network on the uplink CCCH1 logical channel.
    public RRC_RRCResumeRequest1 rrcResumeRequest1;

    // RRC_UL_DCCH_Message
    // RRC messages that may be sent from the UE to the network on the uplink DCCH logical channel.
    public RRC_MeasurementReport measurementReport;
    public RRC_RRCReconfigurationComplete rrcReconfigurationComplete;
    public RRC_RRCSetupComplete rrcSetupComplete;
    public RRC_RRCReestablishmentComplete rrcReestablishmentComplete;
    public RRC_RRCResumeComplete rrcResumeComplete;
    public RRC_SecurityModeComplete securityModeComplete;
    public RRC_SecurityModeFailure securityModeFailure;
    public RRC_ULInformationTransfer ulInformationTransfer;
    public RRC_LocationMeasurementIndication locationMeasurementIndication;
    public RRC_UECapabilityInformation ueCapabilityInformation;
    public RRC_CounterCheckResponse counterCheckResponse;
    public RRC_UEAssistanceInformation ueAssistanceInformation;
    public RRC_FailureInformation failureInformation;
    public RRC_ULInformationTransferMRDC ulInformationTransferMRDC;
    public RRC_SCGFailureInformation scgFailureInformation;
    public RRC_SCGFailureInformationEUTRA scgFailureInformationEUTRA;

    public RrcMessage() {
    }

    public RrcMessage(RRC_MIB mib) {
        this.mib = mib;
    }

    public RrcMessage(RRC_SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }

    public RrcMessage(RRC_SIB1 systemInformationBlockType1) {
        this.systemInformationBlockType1 = systemInformationBlockType1;
    }

    public RrcMessage(RRC_RRCReject rrcReject) {
        this.rrcReject = rrcReject;
    }

    public RrcMessage(RRC_RRCSetup rrcSetup) {
        this.rrcSetup = rrcSetup;
    }

    public RrcMessage(RRC_RRCReconfiguration rrcReconfiguration) {
        this.rrcReconfiguration = rrcReconfiguration;
    }

    public RrcMessage(RRC_RRCResume rrcResume) {
        this.rrcResume = rrcResume;
    }

    public RrcMessage(RRC_RRCRelease rrcRelease) {
        this.rrcRelease = rrcRelease;
    }

    public RrcMessage(RRC_RRCReestablishment rrcReestablishment) {
        this.rrcReestablishment = rrcReestablishment;
    }

    public RrcMessage(RRC_SecurityModeCommand securityModeCommand) {
        this.securityModeCommand = securityModeCommand;
    }

    public RrcMessage(RRC_DLInformationTransfer dlInformationTransfer) {
        this.dlInformationTransfer = dlInformationTransfer;
    }

    public RrcMessage(RRC_UECapabilityEnquiry ueCapabilityEnquiry) {
        this.ueCapabilityEnquiry = ueCapabilityEnquiry;
    }

    public RrcMessage(RRC_CounterCheck counterCheck) {
        this.counterCheck = counterCheck;
    }

    public RrcMessage(RRC_MobilityFromNRCommand mobilityFromNRCommand) {
        this.mobilityFromNRCommand = mobilityFromNRCommand;
    }

    public RrcMessage(RRC_Paging paging) {
        this.paging = paging;
    }

    public RrcMessage(RRC_RRCSetupRequest rrcSetupRequest) {
        this.rrcSetupRequest = rrcSetupRequest;
    }

    public RrcMessage(RRC_RRCResumeRequest rrcResumeRequest) {
        this.rrcResumeRequest = rrcResumeRequest;
    }

    public RrcMessage(RRC_RRCReestablishmentRequest rrcReestablishmentRequest) {
        this.rrcReestablishmentRequest = rrcReestablishmentRequest;
    }

    public RrcMessage(RRC_RRCSystemInfoRequest rrcSystemInfoRequest) {
        this.rrcSystemInfoRequest = rrcSystemInfoRequest;
    }

    public RrcMessage(RRC_RRCResumeRequest1 rrcResumeRequest1) {
        this.rrcResumeRequest1 = rrcResumeRequest1;
    }

    public RrcMessage(RRC_MeasurementReport measurementReport) {
        this.measurementReport = measurementReport;
    }

    public RrcMessage(RRC_RRCReconfigurationComplete rrcReconfigurationComplete) {
        this.rrcReconfigurationComplete = rrcReconfigurationComplete;
    }

    public RrcMessage(RRC_RRCSetupComplete rrcSetupComplete) {
        this.rrcSetupComplete = rrcSetupComplete;
    }

    public RrcMessage(RRC_RRCReestablishmentComplete rrcReestablishmentComplete) {
        this.rrcReestablishmentComplete = rrcReestablishmentComplete;
    }

    public RrcMessage(RRC_RRCResumeComplete rrcResumeComplete) {
        this.rrcResumeComplete = rrcResumeComplete;
    }

    public RrcMessage(RRC_SecurityModeComplete securityModeComplete) {
        this.securityModeComplete = securityModeComplete;
    }

    public RrcMessage(RRC_SecurityModeFailure securityModeFailure) {
        this.securityModeFailure = securityModeFailure;
    }

    public RrcMessage(RRC_ULInformationTransfer ulInformationTransfer) {
        this.ulInformationTransfer = ulInformationTransfer;
    }

    public RrcMessage(RRC_LocationMeasurementIndication locationMeasurementIndication) {
        this.locationMeasurementIndication = locationMeasurementIndication;
    }

    public RrcMessage(RRC_UECapabilityInformation ueCapabilityInformation) {
        this.ueCapabilityInformation = ueCapabilityInformation;
    }

    public RrcMessage(RRC_CounterCheckResponse counterCheckResponse) {
        this.counterCheckResponse = counterCheckResponse;
    }

    public RrcMessage(RRC_UEAssistanceInformation ueAssistanceInformation) {
        this.ueAssistanceInformation = ueAssistanceInformation;
    }

    public RrcMessage(RRC_FailureInformation failureInformation) {
        this.failureInformation = failureInformation;
    }

    public RrcMessage(RRC_ULInformationTransferMRDC ulInformationTransferMRDC) {
        this.ulInformationTransferMRDC = ulInformationTransferMRDC;
    }

    public RrcMessage(RRC_SCGFailureInformation scgFailureInformation) {
        this.scgFailureInformation = scgFailureInformation;
    }

    public RrcMessage(RRC_SCGFailureInformationEUTRA scgFailureInformationEUTRA) {
        this.scgFailureInformationEUTRA = scgFailureInformationEUTRA;
    }

}

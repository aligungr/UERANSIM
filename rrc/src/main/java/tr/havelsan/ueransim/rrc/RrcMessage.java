/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.*;
import tr.havelsan.ueransim.rrc.asn.sequences.*;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

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

    public static RrcMessage fromPduSequence(AsnSequence pdu) {
        if (pdu instanceof RRC_BCCH_BCH_Message) {
            var msg = ((RRC_BCCH_BCH_Message) pdu).message;

            if (msg.mib != null) return new RrcMessage(msg.mib);

        } else if (pdu instanceof RRC_BCCH_DL_SCH_Message) {
            var msg = ((RRC_BCCH_DL_SCH_Message) pdu).message;

            if (msg.c1.systemInformation != null) return new RrcMessage(msg.c1.systemInformation);
            if (msg.c1.systemInformationBlockType1 != null) return new RrcMessage(msg.c1.systemInformationBlockType1);

        } else if (pdu instanceof RRC_DL_CCCH_Message) {
            var msg = ((RRC_DL_CCCH_Message) pdu).message;

            if (msg.c1.rrcReject != null) return new RrcMessage(msg.c1.rrcReject);
            if (msg.c1.rrcSetup != null) return new RrcMessage(msg.c1.rrcSetup);

        } else if (pdu instanceof RRC_DL_DCCH_Message) {
            var msg = ((RRC_DL_DCCH_Message) pdu).message;

            if (msg.c1.rrcReconfiguration != null) return new RrcMessage(msg.c1.rrcReconfiguration);
            if (msg.c1.rrcResume != null) return new RrcMessage(msg.c1.rrcResume);
            if (msg.c1.rrcRelease != null) return new RrcMessage(msg.c1.rrcRelease);
            if (msg.c1.rrcReestablishment != null) return new RrcMessage(msg.c1.rrcReestablishment);
            if (msg.c1.securityModeCommand != null) return new RrcMessage(msg.c1.securityModeCommand);
            if (msg.c1.dlInformationTransfer != null) return new RrcMessage(msg.c1.dlInformationTransfer);
            if (msg.c1.ueCapabilityEnquiry != null) return new RrcMessage(msg.c1.ueCapabilityEnquiry);
            if (msg.c1.counterCheck != null) return new RrcMessage(msg.c1.counterCheck);
            if (msg.c1.mobilityFromNRCommand != null) return new RrcMessage(msg.c1.mobilityFromNRCommand);

        } else if (pdu instanceof RRC_PCCH_Message) {
            var msg = ((RRC_PCCH_Message) pdu).message;

            if (msg.c1.paging != null) return new RrcMessage(msg.c1.paging);

        } else if (pdu instanceof RRC_UL_CCCH_Message) {
            var msg = ((RRC_UL_CCCH_Message) pdu).message;

            if (msg.c1.rrcSetupRequest != null) return new RrcMessage(msg.c1.rrcSetupRequest);
            if (msg.c1.rrcReestablishmentRequest != null) return new RrcMessage(msg.c1.rrcReestablishmentRequest);
            if (msg.c1.rrcResumeRequest != null) return new RrcMessage(msg.c1.rrcResumeRequest);
            if (msg.c1.rrcSystemInfoRequest != null) return new RrcMessage(msg.c1.rrcSystemInfoRequest);

        } else if (pdu instanceof RRC_UL_CCCH1_Message) {
            var msg = ((RRC_UL_CCCH1_Message) pdu).message;

            if (msg.c1.rrcResumeRequest1 != null) return new RrcMessage(msg.c1.rrcResumeRequest1);

        } else if (pdu instanceof RRC_UL_DCCH_Message) {
            var msg = ((RRC_UL_DCCH_Message) pdu).message;

            if (msg.c1.measurementReport != null) return new RrcMessage(msg.c1.measurementReport);
            if (msg.c1.rrcReconfigurationComplete != null) return new RrcMessage(msg.c1.rrcReconfigurationComplete);
            if (msg.c1.rrcSetupComplete != null) return new RrcMessage(msg.c1.rrcSetupComplete);
            if (msg.c1.rrcReestablishmentComplete != null) return new RrcMessage(msg.c1.rrcReestablishmentComplete);
            if (msg.c1.rrcResumeComplete != null) return new RrcMessage(msg.c1.rrcResumeComplete);
            if (msg.c1.securityModeComplete != null) return new RrcMessage(msg.c1.securityModeComplete);
            if (msg.c1.securityModeFailure != null) return new RrcMessage(msg.c1.securityModeFailure);
            if (msg.c1.ulInformationTransfer != null) return new RrcMessage(msg.c1.ulInformationTransfer);
            if (msg.c1.locationMeasurementIndication != null)
                return new RrcMessage(msg.c1.locationMeasurementIndication);
            if (msg.c1.ueCapabilityInformation != null) return new RrcMessage(msg.c1.ueCapabilityInformation);
            if (msg.c1.counterCheckResponse != null) return new RrcMessage(msg.c1.counterCheckResponse);
            if (msg.c1.ueAssistanceInformation != null) return new RrcMessage(msg.c1.ueAssistanceInformation);
            if (msg.c1.failureInformation != null) return new RrcMessage(msg.c1.failureInformation);
            if (msg.c1.ulInformationTransferMRDC != null) return new RrcMessage(msg.c1.ulInformationTransferMRDC);
            if (msg.c1.scgFailureInformation != null) return new RrcMessage(msg.c1.scgFailureInformation);
            if (msg.c1.scgFailureInformationEUTRA != null) return new RrcMessage(msg.c1.scgFailureInformationEUTRA);
        }

        throw new RuntimeException("invalid RRC PDU Sequence");
    }

    public AsnSequence getPduSequence() {
        if (mib != null) {
            var pdu = new RRC_BCCH_BCH_Message();
            pdu.message = new RRC_BCCH_BCH_MessageType();
            pdu.message.mib = mib;
            return pdu;
        }
        if (systemInformation != null) {
            var pdu = new RRC_BCCH_DL_SCH_Message();
            pdu.message = new RRC_BCCH_DL_SCH_MessageType();
            pdu.message.c1 = new RRC_BCCH_DL_SCH_MessageType.RRC_c1_12();
            pdu.message.c1.systemInformation = systemInformation;
            return pdu;
        }
        if (systemInformationBlockType1 != null) {
            var pdu = new RRC_BCCH_DL_SCH_Message();
            pdu.message = new RRC_BCCH_DL_SCH_MessageType();
            pdu.message.c1 = new RRC_BCCH_DL_SCH_MessageType.RRC_c1_12();
            pdu.message.c1.systemInformationBlockType1 = systemInformationBlockType1;
            return pdu;
        }
        if (rrcReject != null) {
            var pdu = new RRC_DL_CCCH_Message();
            pdu.message = new RRC_DL_CCCH_MessageType();
            pdu.message.c1 = new RRC_DL_CCCH_MessageType.RRC_c1_9();
            pdu.message.c1.rrcReject = rrcReject;
        }
        if (rrcSetup != null) {
            var pdu = new RRC_DL_CCCH_Message();
            pdu.message = new RRC_DL_CCCH_MessageType();
            pdu.message.c1 = new RRC_DL_CCCH_MessageType.RRC_c1_9();
            pdu.message.c1.rrcSetup = rrcSetup;
        }
        if (rrcReconfiguration != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.rrcReconfiguration = rrcReconfiguration;
            return pdu;
        }
        if (rrcResume != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.rrcResume = rrcResume;
            return pdu;
        }
        if (rrcRelease != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.rrcRelease = rrcRelease;
            return pdu;
        }
        if (rrcReestablishment != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.rrcReestablishment = rrcReestablishment;
            return pdu;
        }
        if (securityModeCommand != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.securityModeCommand = securityModeCommand;
            return pdu;
        }
        if (dlInformationTransfer != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.dlInformationTransfer = dlInformationTransfer;
            return pdu;
        }
        if (ueCapabilityEnquiry != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.ueCapabilityEnquiry = ueCapabilityEnquiry;
            return pdu;
        }
        if (counterCheck != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.counterCheck = counterCheck;
            return pdu;
        }
        if (mobilityFromNRCommand != null) {
            var pdu = new RRC_DL_DCCH_Message();
            pdu.message = new RRC_DL_DCCH_MessageType();
            pdu.message.c1 = new RRC_DL_DCCH_MessageType.RRC_c1_8();
            pdu.message.c1.mobilityFromNRCommand = mobilityFromNRCommand;
            return pdu;
        }
        if (paging != null) {
            var pdu = new RRC_PCCH_Message();
            pdu.message = new RRC_PCCH_MessageType();
            pdu.message.c1 = new RRC_PCCH_MessageType.RRC_c1_5();
            pdu.message.c1.paging = paging;
            return pdu;
        }
        if (rrcSetupRequest != null) {
            var pdu = new RRC_UL_CCCH_Message();
            pdu.message = new RRC_UL_CCCH_MessageType();
            pdu.message.c1 = new RRC_UL_CCCH_MessageType.RRC_c1_1();
            pdu.message.c1.rrcSetupRequest = rrcSetupRequest;
            return pdu;
        }
        if (rrcResumeRequest != null) {
            var pdu = new RRC_UL_CCCH_Message();
            pdu.message = new RRC_UL_CCCH_MessageType();
            pdu.message.c1 = new RRC_UL_CCCH_MessageType.RRC_c1_1();
            pdu.message.c1.rrcResumeRequest = rrcResumeRequest;
            return pdu;
        }
        if (rrcReestablishmentRequest != null) {
            var pdu = new RRC_UL_CCCH_Message();
            pdu.message = new RRC_UL_CCCH_MessageType();
            pdu.message.c1 = new RRC_UL_CCCH_MessageType.RRC_c1_1();
            pdu.message.c1.rrcReestablishmentRequest = rrcReestablishmentRequest;
            return pdu;
        }
        if (rrcSystemInfoRequest != null) {
            var pdu = new RRC_UL_CCCH_Message();
            pdu.message = new RRC_UL_CCCH_MessageType();
            pdu.message.c1 = new RRC_UL_CCCH_MessageType.RRC_c1_1();
            pdu.message.c1.rrcSystemInfoRequest = rrcSystemInfoRequest;
            return pdu;
        }
        if (rrcResumeRequest1 != null) {
            var pdu = new RRC_UL_CCCH1_Message();
            pdu.message = new RRC_UL_CCCH1_MessageType();
            pdu.message.c1 = new RRC_UL_CCCH1_MessageType.RRC_c1_2();
            pdu.message.c1.rrcResumeRequest1 = rrcResumeRequest1;
            return pdu;
        }
        if (measurementReport != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.measurementReport = measurementReport;
            return pdu;
        }
        if (rrcReconfigurationComplete != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.rrcReconfigurationComplete = rrcReconfigurationComplete;
            return pdu;
        }
        if (rrcSetupComplete != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.rrcSetupComplete = rrcSetupComplete;
            return pdu;
        }
        if (rrcReestablishmentComplete != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.rrcReestablishmentComplete = rrcReestablishmentComplete;
            return pdu;
        }
        if (rrcResumeComplete != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.rrcResumeComplete = rrcResumeComplete;
            return pdu;
        }
        if (securityModeComplete != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.securityModeComplete = securityModeComplete;
            return pdu;
        }
        if (securityModeFailure != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.securityModeFailure = securityModeFailure;
            return pdu;
        }
        if (ulInformationTransfer != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.ulInformationTransfer = ulInformationTransfer;
            return pdu;
        }
        if (locationMeasurementIndication != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.locationMeasurementIndication = locationMeasurementIndication;
            return pdu;
        }
        if (ueCapabilityInformation != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.ueCapabilityInformation = ueCapabilityInformation;
            return pdu;
        }
        if (counterCheckResponse != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.counterCheckResponse = counterCheckResponse;
            return pdu;
        }
        if (ueAssistanceInformation != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.ueAssistanceInformation = ueAssistanceInformation;
            return pdu;
        }
        if (failureInformation != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.failureInformation = failureInformation;
            return pdu;
        }
        if (ulInformationTransferMRDC != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.ulInformationTransferMRDC = ulInformationTransferMRDC;
            return pdu;
        }
        if (scgFailureInformation != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.scgFailureInformation = scgFailureInformation;
            return pdu;
        }
        if (scgFailureInformationEUTRA != null) {
            var pdu = new RRC_UL_DCCH_Message();
            pdu.message = new RRC_UL_DCCH_MessageType();
            pdu.message.c1 = new RRC_UL_DCCH_MessageType.RRC_c1_4();
            pdu.message.c1.scgFailureInformationEUTRA = scgFailureInformationEUTRA;
            return pdu;
        }
        throw new IncorrectImplementationException("getPduSequence");
    }

}

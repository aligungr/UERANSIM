/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_UL_DCCH_MessageType__c1 extends RRC_Choice {

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

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measurementReport","rrcReconfigurationComplete","rrcSetupComplete","rrcReestablishmentComplete","rrcResumeComplete","securityModeComplete","securityModeFailure","ulInformationTransfer","locationMeasurementIndication","ueCapabilityInformation","counterCheckResponse","ueAssistanceInformation","failureInformation","ulInformationTransferMRDC","scgFailureInformation","scgFailureInformationEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measurementReport","rrcReconfigurationComplete","rrcSetupComplete","rrcReestablishmentComplete","rrcResumeComplete","securityModeComplete","securityModeFailure","ulInformationTransfer","locationMeasurementIndication","ueCapabilityInformation","counterCheckResponse","ueAssistanceInformation","failureInformation","ulInformationTransferMRDC","scgFailureInformation","scgFailureInformationEUTRA" };
    }

}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_UL_DCCH_MessageType extends AsnChoice {
    public RRC_c1_4 c1;
    public RRC_messageClassExtension_1 messageClassExtension;

    public static class RRC_messageClassExtension_1 extends AsnSequence {
    }

    public static class RRC_c1_4 extends AsnChoice {
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
    }
}


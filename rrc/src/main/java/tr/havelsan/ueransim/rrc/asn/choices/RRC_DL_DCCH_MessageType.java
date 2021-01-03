/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_DL_DCCH_MessageType extends AsnChoice {
    public RRC_c1_8 c1;
    public RRC_messageClassExtension_4 messageClassExtension;

    public static class RRC_messageClassExtension_4 extends AsnSequence {
    }

    public static class RRC_c1_8 extends AsnChoice {
        public RRC_RRCReconfiguration rrcReconfiguration;
        public RRC_RRCResume rrcResume;
        public RRC_RRCRelease rrcRelease;
        public RRC_RRCReestablishment rrcReestablishment;
        public RRC_SecurityModeCommand securityModeCommand;
        public RRC_DLInformationTransfer dlInformationTransfer;
        public RRC_UECapabilityEnquiry ueCapabilityEnquiry;
        public RRC_CounterCheck counterCheck;
        public RRC_MobilityFromNRCommand mobilityFromNRCommand;
        public AsnNull spare7;
        public AsnNull spare6;
        public AsnNull spare5;
        public AsnNull spare4;
        public AsnNull spare3;
        public AsnNull spare2;
        public AsnNull spare1;
    }
}


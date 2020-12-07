/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCReject;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSetup;

public class RRC_DL_CCCH_MessageType extends AsnChoice {
    public RRC_c1_9 c1;
    public RRC_messageClassExtension_8 messageClassExtension;

    public static class RRC_c1_9 extends AsnChoice {
        public RRC_RRCReject rrcReject;
        public RRC_RRCSetup rrcSetup;
        public AsnNull spare2;
        public AsnNull spare1;
    }

    public static class RRC_messageClassExtension_8 extends AsnSequence {
    }
}


/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RLC_Config;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRB_Identity;

public class RRC_RLC_BearerConfig extends AsnSequence {
    public RRC_LogicalChannelIdentity logicalChannelIdentity; // mandatory
    public RRC_servedRadioBearer servedRadioBearer; // optional
    public RRC_reestablishRLC reestablishRLC; // optional
    public RRC_RLC_Config rlc_Config; // optional
    public RRC_LogicalChannelConfig mac_LogicalChannelConfig; // optional

    public static class RRC_servedRadioBearer extends AsnChoice {
        public RRC_SRB_Identity srb_Identity;
        public RRC_DRB_Identity drb_Identity;
    }

    public static class RRC_reestablishRLC extends AsnEnumerated {
        public static final RRC_reestablishRLC TRUE = new RRC_reestablishRLC(0);
    
        private RRC_reestablishRLC(long value) {
            super(value);
        }
    }
}


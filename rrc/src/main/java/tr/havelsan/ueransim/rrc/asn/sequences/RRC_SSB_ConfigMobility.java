/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;

public class RRC_SSB_ConfigMobility extends AsnSequence {
    public RRC_SetupRelease_SSB_ToMeasure ssb_ToMeasure; // optional
    public AsnBoolean deriveSSB_IndexFromCell; // mandatory
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement; // optional

    public static class RRC_SetupRelease_SSB_ToMeasure extends AsnChoice {
        public AsnNull release;
        public RRC_SSB_ToMeasure setup;
    }
}


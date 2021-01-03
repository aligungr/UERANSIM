/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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


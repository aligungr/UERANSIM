/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PDCCH_ServingCellConfig extends AsnSequence {
    public RRC_SetupRelease_SlotFormatIndicator slotFormatIndicator; // optional

    public static class RRC_SetupRelease_SlotFormatIndicator extends AsnChoice {
        public AsnNull release;
        public RRC_SlotFormatIndicator setup;
    }
}


/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NumberOfCarriers;

public class RRC_ProcessingParameters extends AsnSequence {
    public RRC_fallback fallback; // mandatory
    public RRC_differentTB_PerSlot differentTB_PerSlot; // optional

    public static class RRC_differentTB_PerSlot extends AsnSequence {
        public RRC_NumberOfCarriers upto1; // optional
        public RRC_NumberOfCarriers upto2; // optional
        public RRC_NumberOfCarriers upto4; // optional
        public RRC_NumberOfCarriers upto7; // optional
    }

    public static class RRC_fallback extends AsnEnumerated {
        public static final RRC_fallback SC = new RRC_fallback(0);
        public static final RRC_fallback CAP1_ONLY = new RRC_fallback(1);
    
        private RRC_fallback(long value) {
            super(value);
        }
    }
}


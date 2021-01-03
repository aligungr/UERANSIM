/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_PUCCH_PowerControl extends AsnSequence {
    public AsnInteger deltaF_PUCCH_f0; // optional, VALUE(-16..15)
    public AsnInteger deltaF_PUCCH_f1; // optional, VALUE(-16..15)
    public AsnInteger deltaF_PUCCH_f2; // optional, VALUE(-16..15)
    public AsnInteger deltaF_PUCCH_f3; // optional, VALUE(-16..15)
    public AsnInteger deltaF_PUCCH_f4; // optional, VALUE(-16..15)
    public RRC_p0_Set p0_Set; // optional, SIZE(1..8)
    public RRC_pathlossReferenceRSs pathlossReferenceRSs; // optional, SIZE(1..4)
    public RRC_twoPUCCH_PC_AdjustmentStates twoPUCCH_PC_AdjustmentStates; // optional

    // SIZE(1..8)
    public static class RRC_p0_Set extends AsnSequenceOf<RRC_P0_PUCCH> {
    }

    // SIZE(1..4)
    public static class RRC_pathlossReferenceRSs extends AsnSequenceOf<RRC_PUCCH_PathlossReferenceRS> {
    }

    public static class RRC_twoPUCCH_PC_AdjustmentStates extends AsnEnumerated {
        public static final RRC_twoPUCCH_PC_AdjustmentStates TWOSTATES = new RRC_twoPUCCH_PC_AdjustmentStates(0);
    
        private RRC_twoPUCCH_PC_AdjustmentStates(long value) {
            super(value);
        }
    }
}


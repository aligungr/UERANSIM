/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_SRS_TPC_PDCCH_Config extends AsnSequence {
    public RRC_srs_CC_SetIndexlist srs_CC_SetIndexlist; // optional, SIZE(1..4)

    // SIZE(1..4)
    public static class RRC_srs_CC_SetIndexlist extends AsnSequenceOf<RRC_SRS_CC_SetIndex> {
    }
}


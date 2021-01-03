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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_UplinkTxDirectCurrentCell extends AsnSequence {
    public RRC_ServCellIndex servCellIndex; // mandatory
    public RRC_uplinkDirectCurrentBWP uplinkDirectCurrentBWP; // mandatory, SIZE(1..4)
    public RRC_ext1_1 ext1; // optional

    public static class RRC_ext1_1 extends AsnSequence {
        public RRC_uplinkDirectCurrentBWP_SUL uplinkDirectCurrentBWP_SUL; // optional, SIZE(1..4)
    
        // SIZE(1..4)
        public static class RRC_uplinkDirectCurrentBWP_SUL extends AsnSequenceOf<RRC_UplinkTxDirectCurrentBWP> {
        }
    }

    // SIZE(1..4)
    public static class RRC_uplinkDirectCurrentBWP extends AsnSequenceOf<RRC_UplinkTxDirectCurrentBWP> {
    }
}


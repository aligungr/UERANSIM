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

public class RRC_QuantityConfig extends AsnSequence {
    public RRC_quantityConfigNR_List quantityConfigNR_List; // optional, SIZE(1..2)
    public RRC_ext1_17 ext1; // optional

    public static class RRC_ext1_17 extends AsnSequence {
        public RRC_FilterConfig quantityConfigEUTRA; // optional
    }

    // SIZE(1..2)
    public static class RRC_quantityConfigNR_List extends AsnSequenceOf<RRC_QuantityConfigNR> {
    }
}


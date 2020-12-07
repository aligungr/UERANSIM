/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SRS_TPC_CommandConfig extends AsnSequence {
    public AsnInteger startingBitOfFormat2_3; // optional, VALUE(1..31)
    public AsnInteger fieldTypeFormat2_3; // optional, VALUE(0..1)
    public RRC_ext1_46 ext1; // optional

    public static class RRC_ext1_46 extends AsnSequence {
        public AsnInteger startingBitOfFormat2_3SUL_v1530; // optional, VALUE(1..31)
    }
}


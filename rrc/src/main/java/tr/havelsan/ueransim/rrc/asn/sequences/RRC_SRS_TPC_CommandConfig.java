/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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


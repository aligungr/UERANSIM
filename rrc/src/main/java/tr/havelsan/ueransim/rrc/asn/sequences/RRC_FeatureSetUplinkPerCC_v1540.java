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

public class RRC_FeatureSetUplinkPerCC_v1540 extends AsnSequence {
    public RRC_mimo_NonCB_PUSCH mimo_NonCB_PUSCH; // optional

    public static class RRC_mimo_NonCB_PUSCH extends AsnSequence {
        public AsnInteger maxNumberSRS_ResourcePerSet; // mandatory, VALUE(1..4)
        public AsnInteger maxNumberSimultaneousSRS_ResourceTx; // mandatory, VALUE(1..4)
    }
}


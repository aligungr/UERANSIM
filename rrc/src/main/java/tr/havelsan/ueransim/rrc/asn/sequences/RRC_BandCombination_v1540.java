/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_BandCombination_v1540 extends AsnSequence {
    public RRC_bandList_v1540 bandList_v1540; // mandatory, SIZE(1..32)
    public RRC_CA_ParametersNR_v1540 ca_ParametersNR_v1540; // optional

    // SIZE(1..32)
    public static class RRC_bandList_v1540 extends AsnSequenceOf<RRC_BandParameters_v1540> {
    }
}


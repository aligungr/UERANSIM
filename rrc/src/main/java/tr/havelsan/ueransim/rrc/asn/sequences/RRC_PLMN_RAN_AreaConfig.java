/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_PLMN_RAN_AreaConfig extends AsnSequence {
    public RRC_PLMN_Identity plmn_Identity; // optional
    public RRC_ran_Area ran_Area; // mandatory, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_ran_Area extends AsnSequenceOf<RRC_RAN_AreaConfig> {
    }
}


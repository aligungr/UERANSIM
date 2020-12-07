/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_AS_Config extends AsnSequence {
    public AsnOctetString rrcReconfiguration; // mandatory, SIZE(0..MAX)
    public RRC_ext1_39 ext1; // optional

    public static class RRC_ext1_39 extends AsnSequence {
        public AsnOctetString sourceRB_SN_Config; // optional, SIZE(0..MAX)
        public AsnOctetString sourceSCG_NR_Config; // optional, SIZE(0..MAX)
        public AsnOctetString sourceSCG_EUTRA_Config; // optional
    }
}


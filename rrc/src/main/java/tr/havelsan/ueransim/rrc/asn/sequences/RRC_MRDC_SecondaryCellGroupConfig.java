/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MRDC_SecondaryCellGroupConfig extends AsnSequence {
    public RRC_mrdc_ReleaseAndAdd mrdc_ReleaseAndAdd; // optional
    public RRC_mrdc_SecondaryCellGroup mrdc_SecondaryCellGroup; // mandatory

    public static class RRC_mrdc_SecondaryCellGroup extends AsnChoice {
        public AsnOctetString nr_SCG; // SIZE(0..MAX)
        public AsnOctetString eutra_SCG;
    }

    public static class RRC_mrdc_ReleaseAndAdd extends AsnEnumerated {
        public static final RRC_mrdc_ReleaseAndAdd TRUE = new RRC_mrdc_ReleaseAndAdd(0);
    
        private RRC_mrdc_ReleaseAndAdd(long value) {
            super(value);
        }
    }
}


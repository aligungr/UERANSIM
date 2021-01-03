/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCResume_IEs extends AsnSequence {
    public RRC_RadioBearerConfig radioBearerConfig; // optional
    public AsnOctetString masterCellGroup; // optional, SIZE(0..MAX)
    public RRC_MeasConfig measConfig; // optional
    public RRC_fullConfig_2 fullConfig; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_RRCResume_v1560_IEs nonCriticalExtension; // optional

    public static class RRC_fullConfig_2 extends AsnEnumerated {
        public static final RRC_fullConfig_2 TRUE = new RRC_fullConfig_2(0);
    
        private RRC_fullConfig_2(long value) {
            super(value);
        }
    }
}


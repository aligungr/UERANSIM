/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUCCH_ConfigCommon extends AsnSequence {
    public AsnInteger pucch_ResourceCommon; // optional, VALUE(0..15)
    public RRC_pucch_GroupHopping pucch_GroupHopping; // mandatory
    public AsnInteger hoppingId; // optional, VALUE(0..1023)
    public AsnInteger p0_nominal; // optional, VALUE(-202..24)

    public static class RRC_pucch_GroupHopping extends AsnEnumerated {
        public static final RRC_pucch_GroupHopping NEITHER = new RRC_pucch_GroupHopping(0);
        public static final RRC_pucch_GroupHopping ENABLE = new RRC_pucch_GroupHopping(1);
        public static final RRC_pucch_GroupHopping DISABLE = new RRC_pucch_GroupHopping(2);
    
        private RRC_pucch_GroupHopping(long value) {
            super(value);
        }
    }
}


/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_PagingUE_Identity;

public class RRC_PagingRecord extends AsnSequence {
    public RRC_PagingUE_Identity ue_Identity; // mandatory
    public RRC_accessType accessType; // optional

    public static class RRC_accessType extends AsnEnumerated {
        public static final RRC_accessType NON3GPP = new RRC_accessType(0);
    
        private RRC_accessType(long value) {
            super(value);
        }
    }
}


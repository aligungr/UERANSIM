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

public class RRC_SDAP_Parameters extends AsnSequence {
    public RRC_as_ReflectiveQoS as_ReflectiveQoS; // optional

    public static class RRC_as_ReflectiveQoS extends AsnEnumerated {
        public static final RRC_as_ReflectiveQoS TRUE = new RRC_as_ReflectiveQoS(0);
    
        private RRC_as_ReflectiveQoS(long value) {
            super(value);
        }
    }
}


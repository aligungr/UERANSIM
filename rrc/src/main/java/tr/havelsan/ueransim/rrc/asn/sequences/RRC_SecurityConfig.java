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

public class RRC_SecurityConfig extends AsnSequence {
    public RRC_SecurityAlgorithmConfig securityAlgorithmConfig; // optional
    public RRC_keyToUse keyToUse; // optional

    public static class RRC_keyToUse extends AsnEnumerated {
        public static final RRC_keyToUse MASTER = new RRC_keyToUse(0);
        public static final RRC_keyToUse SECONDARY = new RRC_keyToUse(1);
    
        private RRC_keyToUse(long value) {
            super(value);
        }
    }
}


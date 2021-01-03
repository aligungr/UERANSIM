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

public class RRC_BCCH_Config extends AsnSequence {
    public RRC_modificationPeriodCoeff modificationPeriodCoeff; // mandatory

    public static class RRC_modificationPeriodCoeff extends AsnEnumerated {
        public static final RRC_modificationPeriodCoeff N2 = new RRC_modificationPeriodCoeff(0);
        public static final RRC_modificationPeriodCoeff N4 = new RRC_modificationPeriodCoeff(1);
        public static final RRC_modificationPeriodCoeff N8 = new RRC_modificationPeriodCoeff(2);
        public static final RRC_modificationPeriodCoeff N16 = new RRC_modificationPeriodCoeff(3);
    
        private RRC_modificationPeriodCoeff(long value) {
            super(value);
        }
    }
}


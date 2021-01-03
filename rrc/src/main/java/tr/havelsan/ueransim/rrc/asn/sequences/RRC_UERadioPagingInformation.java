/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UERadioPagingInformation extends AsnSequence {
    public RRC_criticalExtensions_30 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_30 extends AsnChoice {
        public RRC_c1_3 c1;
        public RRC_criticalExtensionsFuture_2 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_2 extends AsnSequence {
        }
    
        public static class RRC_c1_3 extends AsnChoice {
            public RRC_UERadioPagingInformation_IEs ueRadioPagingInformation;
            public AsnNull spare7;
            public AsnNull spare6;
            public AsnNull spare5;
            public AsnNull spare4;
            public AsnNull spare3;
            public AsnNull spare2;
            public AsnNull spare1;
        }
    }
}


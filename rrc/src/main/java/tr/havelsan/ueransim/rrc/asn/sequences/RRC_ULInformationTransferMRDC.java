/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ULInformationTransferMRDC extends AsnSequence {
    public RRC_criticalExtensions_33 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_33 extends AsnChoice {
        public RRC_c1_14 c1;
        public RRC_criticalExtensionsFuture_33 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_33 extends AsnSequence {
        }
    
        public static class RRC_c1_14 extends AsnChoice {
            public RRC_ULInformationTransferMRDC_IEs ulInformationTransferMRDC;
            public AsnNull spare3;
            public AsnNull spare2;
            public AsnNull spare1;
        }
    }
}


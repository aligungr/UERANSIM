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

public class RRC_UE_CapabilityRequestFilterCommon extends AsnSequence {
    public RRC_mrdc_Request mrdc_Request; // optional

    public static class RRC_mrdc_Request extends AsnSequence {
        public RRC_omitEN_DC omitEN_DC; // optional
        public RRC_includeNR_DC includeNR_DC; // optional
        public RRC_includeNE_DC includeNE_DC; // optional
    
        public static class RRC_includeNE_DC extends AsnEnumerated {
            public static final RRC_includeNE_DC TRUE = new RRC_includeNE_DC(0);
        
            private RRC_includeNE_DC(long value) {
                super(value);
            }
        }
    
        public static class RRC_includeNR_DC extends AsnEnumerated {
            public static final RRC_includeNR_DC TRUE = new RRC_includeNR_DC(0);
        
            private RRC_includeNR_DC(long value) {
                super(value);
            }
        }
    
        public static class RRC_omitEN_DC extends AsnEnumerated {
            public static final RRC_omitEN_DC TRUE = new RRC_omitEN_DC(0);
        
            private RRC_omitEN_DC(long value) {
                super(value);
            }
        }
    }
}


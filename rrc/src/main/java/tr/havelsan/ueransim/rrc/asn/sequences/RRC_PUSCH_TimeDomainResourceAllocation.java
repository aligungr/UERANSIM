/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUSCH_TimeDomainResourceAllocation extends AsnSequence {
    public AsnInteger k2; // optional, VALUE(0..32)
    public RRC_mappingType_1 mappingType; // mandatory
    public AsnInteger startSymbolAndLength; // mandatory, VALUE(0..127)

    public static class RRC_mappingType_1 extends AsnEnumerated {
        public static final RRC_mappingType_1 TYPEA = new RRC_mappingType_1(0);
        public static final RRC_mappingType_1 TYPEB = new RRC_mappingType_1(1);
    
        private RRC_mappingType_1(long value) {
            super(value);
        }
    }
}


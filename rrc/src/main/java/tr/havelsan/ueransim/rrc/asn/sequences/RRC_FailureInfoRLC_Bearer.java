/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;

public class RRC_FailureInfoRLC_Bearer extends AsnSequence {
    public RRC_CellGroupId cellGroupId; // mandatory
    public RRC_LogicalChannelIdentity logicalChannelIdentity; // mandatory
    public RRC_failureType_3 failureType; // mandatory

    public static class RRC_failureType_3 extends AsnEnumerated {
        public static final RRC_failureType_3 DUPLICATION = new RRC_failureType_3(0);
        public static final RRC_failureType_3 SPARE3 = new RRC_failureType_3(1);
        public static final RRC_failureType_3 SPARE2 = new RRC_failureType_3(2);
        public static final RRC_failureType_3 SPARE1 = new RRC_failureType_3(3);
    
        private RRC_failureType_3(long value) {
            super(value);
        }
    }
}


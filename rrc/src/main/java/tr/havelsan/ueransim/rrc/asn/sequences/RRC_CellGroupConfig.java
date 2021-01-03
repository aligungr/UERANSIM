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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SCellIndex;

public class RRC_CellGroupConfig extends AsnSequence {
    public RRC_CellGroupId cellGroupId; // mandatory
    public RRC_rlc_BearerToAddModList rlc_BearerToAddModList; // optional, SIZE(1..32)
    public RRC_rlc_BearerToReleaseList rlc_BearerToReleaseList; // optional, SIZE(1..32)
    public RRC_MAC_CellGroupConfig mac_CellGroupConfig; // optional
    public RRC_PhysicalCellGroupConfig physicalCellGroupConfig; // optional
    public RRC_SpCellConfig spCellConfig; // optional
    public RRC_sCellToAddModList sCellToAddModList; // optional, SIZE(1..31)
    public RRC_sCellToReleaseList sCellToReleaseList; // optional, SIZE(1..31)
    public RRC_ext1_24 ext1; // optional

    public static class RRC_ext1_24 extends AsnSequence {
        public RRC_reportUplinkTxDirectCurrent_v1530 reportUplinkTxDirectCurrent_v1530; // optional
    
        public static class RRC_reportUplinkTxDirectCurrent_v1530 extends AsnEnumerated {
            public static final RRC_reportUplinkTxDirectCurrent_v1530 TRUE = new RRC_reportUplinkTxDirectCurrent_v1530(0);
        
            private RRC_reportUplinkTxDirectCurrent_v1530(long value) {
                super(value);
            }
        }
    }

    // SIZE(1..31)
    public static class RRC_sCellToAddModList extends AsnSequenceOf<RRC_SCellConfig> {
    }

    // SIZE(1..32)
    public static class RRC_rlc_BearerToAddModList extends AsnSequenceOf<RRC_RLC_BearerConfig> {
    }

    // SIZE(1..31)
    public static class RRC_sCellToReleaseList extends AsnSequenceOf<RRC_SCellIndex> {
    }

    // SIZE(1..32)
    public static class RRC_rlc_BearerToReleaseList extends AsnSequenceOf<RRC_LogicalChannelIdentity> {
    }
}


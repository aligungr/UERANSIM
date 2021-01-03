/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PDU_SessionID;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_QFI;

public class RRC_SDAP_Config extends AsnSequence {
    public RRC_PDU_SessionID pdu_Session; // mandatory
    public RRC_sdap_HeaderDL sdap_HeaderDL; // mandatory
    public RRC_sdap_HeaderUL sdap_HeaderUL; // mandatory
    public AsnBoolean defaultDRB; // mandatory
    public RRC_mappedQoS_FlowsToAdd mappedQoS_FlowsToAdd; // optional, SIZE(1..64)
    public RRC_mappedQoS_FlowsToRelease mappedQoS_FlowsToRelease; // optional, SIZE(1..64)

    // SIZE(1..64)
    public static class RRC_mappedQoS_FlowsToAdd extends AsnSequenceOf<RRC_QFI> {
    }

    public static class RRC_sdap_HeaderUL extends AsnEnumerated {
        public static final RRC_sdap_HeaderUL PRESENT = new RRC_sdap_HeaderUL(0);
        public static final RRC_sdap_HeaderUL ABSENT = new RRC_sdap_HeaderUL(1);
    
        private RRC_sdap_HeaderUL(long value) {
            super(value);
        }
    }

    // SIZE(1..64)
    public static class RRC_mappedQoS_FlowsToRelease extends AsnSequenceOf<RRC_QFI> {
    }

    public static class RRC_sdap_HeaderDL extends AsnEnumerated {
        public static final RRC_sdap_HeaderDL PRESENT = new RRC_sdap_HeaderDL(0);
        public static final RRC_sdap_HeaderDL ABSENT = new RRC_sdap_HeaderDL(1);
    
        private RRC_sdap_HeaderDL(long value) {
            super(value);
        }
    }
}


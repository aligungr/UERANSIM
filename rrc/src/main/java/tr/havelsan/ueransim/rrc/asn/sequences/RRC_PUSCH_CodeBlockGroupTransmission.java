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

public class RRC_PUSCH_CodeBlockGroupTransmission extends AsnSequence {
    public RRC_maxCodeBlockGroupsPerTransportBlock_2 maxCodeBlockGroupsPerTransportBlock; // mandatory

    public static class RRC_maxCodeBlockGroupsPerTransportBlock_2 extends AsnEnumerated {
        public static final RRC_maxCodeBlockGroupsPerTransportBlock_2 N2 = new RRC_maxCodeBlockGroupsPerTransportBlock_2(0);
        public static final RRC_maxCodeBlockGroupsPerTransportBlock_2 N4 = new RRC_maxCodeBlockGroupsPerTransportBlock_2(1);
        public static final RRC_maxCodeBlockGroupsPerTransportBlock_2 N6 = new RRC_maxCodeBlockGroupsPerTransportBlock_2(2);
        public static final RRC_maxCodeBlockGroupsPerTransportBlock_2 N8 = new RRC_maxCodeBlockGroupsPerTransportBlock_2(3);
    
        private RRC_maxCodeBlockGroupsPerTransportBlock_2(long value) {
            super(value);
        }
    }
}


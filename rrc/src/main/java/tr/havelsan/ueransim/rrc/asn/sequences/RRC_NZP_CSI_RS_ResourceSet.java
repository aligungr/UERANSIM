/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceSetId;

public class RRC_NZP_CSI_RS_ResourceSet extends AsnSequence {
    public RRC_NZP_CSI_RS_ResourceSetId nzp_CSI_ResourceSetId; // mandatory
    public RRC_nzp_CSI_RS_Resources nzp_CSI_RS_Resources; // mandatory, SIZE(1..64)
    public RRC_repetition repetition; // optional
    public AsnInteger aperiodicTriggeringOffset; // optional, VALUE(0..6)
    public RRC_trs_Info trs_Info; // optional

    public static class RRC_trs_Info extends AsnEnumerated {
        public static final RRC_trs_Info TRUE = new RRC_trs_Info(0);
    
        private RRC_trs_Info(long value) {
            super(value);
        }
    }

    // SIZE(1..64)
    public static class RRC_nzp_CSI_RS_Resources extends AsnSequenceOf<RRC_NZP_CSI_RS_ResourceId> {
    }

    public static class RRC_repetition extends AsnEnumerated {
        public static final RRC_repetition ON = new RRC_repetition(0);
        public static final RRC_repetition OFF = new RRC_repetition(1);
    
        private RRC_repetition(long value) {
            super(value);
        }
    }
}


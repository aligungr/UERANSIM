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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceSetId;

public class RRC_SRS_Config extends AsnSequence {
    public RRC_srs_ResourceSetToReleaseList srs_ResourceSetToReleaseList; // optional, SIZE(1..16)
    public RRC_srs_ResourceSetToAddModList srs_ResourceSetToAddModList; // optional, SIZE(1..16)
    public RRC_srs_ResourceToReleaseList srs_ResourceToReleaseList; // optional, SIZE(1..64)
    public RRC_srs_ResourceToAddModList srs_ResourceToAddModList; // optional, SIZE(1..64)
    public RRC_tpc_Accumulation_1 tpc_Accumulation; // optional

    // SIZE(1..16)
    public static class RRC_srs_ResourceSetToReleaseList extends AsnSequenceOf<RRC_SRS_ResourceSetId> {
    }

    public static class RRC_tpc_Accumulation_1 extends AsnEnumerated {
        public static final RRC_tpc_Accumulation_1 DISABLED = new RRC_tpc_Accumulation_1(0);
    
        private RRC_tpc_Accumulation_1(long value) {
            super(value);
        }
    }

    // SIZE(1..16)
    public static class RRC_srs_ResourceSetToAddModList extends AsnSequenceOf<RRC_SRS_ResourceSet> {
    }

    // SIZE(1..64)
    public static class RRC_srs_ResourceToReleaseList extends AsnSequenceOf<RRC_SRS_ResourceId> {
    }

    // SIZE(1..64)
    public static class RRC_srs_ResourceToAddModList extends AsnSequenceOf<RRC_SRS_Resource> {
    }
}


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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRI_PUSCH_PowerControlId;

public class RRC_SRI_PUSCH_PowerControl extends AsnSequence {
    public RRC_SRI_PUSCH_PowerControlId sri_PUSCH_PowerControlId; // mandatory
    public RRC_PUSCH_PathlossReferenceRS_Id sri_PUSCH_PathlossReferenceRS_Id; // mandatory
    public RRC_P0_PUSCH_AlphaSetId sri_P0_PUSCH_AlphaSetId; // mandatory
    public RRC_sri_PUSCH_ClosedLoopIndex sri_PUSCH_ClosedLoopIndex; // mandatory

    public static class RRC_sri_PUSCH_ClosedLoopIndex extends AsnEnumerated {
        public static final RRC_sri_PUSCH_ClosedLoopIndex I0 = new RRC_sri_PUSCH_ClosedLoopIndex(0);
        public static final RRC_sri_PUSCH_ClosedLoopIndex I1 = new RRC_sri_PUSCH_ClosedLoopIndex(1);
    
        private RRC_sri_PUSCH_ClosedLoopIndex(long value) {
            super(value);
        }
    }
}


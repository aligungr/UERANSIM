/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandEntryIndex;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationInfoList;

public class RRC_ConfigRestrictInfoSCG extends AsnSequence {
    public RRC_BandCombinationInfoList allowedBC_ListMRDC; // optional
    public RRC_powerCoordination_FR1 powerCoordination_FR1; // optional
    public RRC_servCellIndexRangeSCG servCellIndexRangeSCG; // optional
    public AsnInteger maxMeasFreqsSCG; // optional, VALUE(1..32)
    public AsnInteger maxMeasIdentitiesSCG_NR; // optional, VALUE(1..62)
    public RRC_ext1_43 ext1; // optional

    public static class RRC_servCellIndexRangeSCG extends AsnSequence {
        public RRC_ServCellIndex lowBound; // mandatory
        public RRC_ServCellIndex upBound; // mandatory
    }

    public static class RRC_powerCoordination_FR1 extends AsnSequence {
        public RRC_P_Max p_maxNR_FR1; // optional
        public RRC_P_Max p_maxEUTRA; // optional
        public RRC_P_Max p_maxUE_FR1; // optional
    }

    public static class RRC_ext1_43 extends AsnSequence {
        public RRC_selectedBandEntriesMN selectedBandEntriesMN; // optional, SIZE(1..32)
        public AsnInteger pdcch_BlindDetectionSCG; // optional, VALUE(1..15)
        public AsnInteger maxNumberROHC_ContextSessionsSN; // optional, VALUE(0..16384)
    
        // SIZE(1..32)
        public static class RRC_selectedBandEntriesMN extends AsnSequenceOf<RRC_BandEntryIndex> {
        }
    }
}


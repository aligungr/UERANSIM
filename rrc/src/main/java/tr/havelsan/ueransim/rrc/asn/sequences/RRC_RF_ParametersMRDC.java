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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_RF_ParametersMRDC extends AsnSequence {
    public RRC_BandCombinationList supportedBandCombinationList; // optional
    public RRC_FreqBandList appliedFreqBandListFilter; // optional
    public RRC_ext1_2 ext1; // optional
    public RRC_ext2_1 ext2; // optional
    public RRC_ext3_1 ext3; // optional

    public static class RRC_ext3_1 extends AsnSequence {
        public RRC_BandCombinationList_v1560 supportedBandCombinationList_v1560; // optional
        public RRC_BandCombinationList supportedBandCombinationListNEDC_Only; // optional
    }

    public static class RRC_ext2_1 extends AsnSequence {
        public RRC_BandCombinationList_v1550 supportedBandCombinationList_v1550; // optional
    }

    public static class RRC_ext1_2 extends AsnSequence {
        public RRC_srs_SwitchingTimeRequested_2 srs_SwitchingTimeRequested; // optional
        public RRC_BandCombinationList_v1540 supportedBandCombinationList_v1540; // optional
    
        public static class RRC_srs_SwitchingTimeRequested_2 extends AsnEnumerated {
            public static final RRC_srs_SwitchingTimeRequested_2 TRUE = new RRC_srs_SwitchingTimeRequested_2(0);
        
            private RRC_srs_SwitchingTimeRequested_2(long value) {
                super(value);
            }
        }
    }
}


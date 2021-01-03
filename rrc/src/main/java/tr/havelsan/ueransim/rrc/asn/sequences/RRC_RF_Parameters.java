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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_RF_Parameters extends AsnSequence {
    public RRC_supportedBandListNR supportedBandListNR; // mandatory, SIZE(1..1024)
    public RRC_BandCombinationList supportedBandCombinationList; // optional
    public RRC_FreqBandList appliedFreqBandListFilter; // optional
    public RRC_ext1_3 ext1; // optional
    public RRC_ext2_4 ext2; // optional
    public RRC_ext3_5 ext3; // optional

    public static class RRC_ext1_3 extends AsnSequence {
        public RRC_BandCombinationList_v1540 supportedBandCombinationList_v1540; // optional
        public RRC_srs_SwitchingTimeRequested_1 srs_SwitchingTimeRequested; // optional
    
        public static class RRC_srs_SwitchingTimeRequested_1 extends AsnEnumerated {
            public static final RRC_srs_SwitchingTimeRequested_1 TRUE = new RRC_srs_SwitchingTimeRequested_1(0);
        
            private RRC_srs_SwitchingTimeRequested_1(long value) {
                super(value);
            }
        }
    }

    // SIZE(1..1024)
    public static class RRC_supportedBandListNR extends AsnSequenceOf<RRC_BandNR> {
    }

    public static class RRC_ext2_4 extends AsnSequence {
        public RRC_BandCombinationList_v1550 supportedBandCombinationList_v1550; // optional
    }

    public static class RRC_ext3_5 extends AsnSequence {
        public RRC_BandCombinationList_v1560 supportedBandCombinationList_v1560; // optional
    }
}


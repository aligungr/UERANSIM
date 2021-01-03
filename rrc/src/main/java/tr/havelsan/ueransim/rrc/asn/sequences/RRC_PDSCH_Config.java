/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DMRS_DownlinkConfig;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDSCH_TimeDomainResourceAllocationList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RateMatchPatternGroup;

public class RRC_PDSCH_Config extends AsnSequence {
    public AsnInteger dataScramblingIdentityPDSCH; // optional, VALUE(0..1023)
    public RRC_SetupRelease_DMRS_DownlinkConfig dmrs_DownlinkForPDSCH_MappingTypeA; // optional
    public RRC_SetupRelease_DMRS_DownlinkConfig dmrs_DownlinkForPDSCH_MappingTypeB; // optional
    public RRC_tci_StatesToAddModList tci_StatesToAddModList; // optional, SIZE(1..128)
    public RRC_tci_StatesToReleaseList tci_StatesToReleaseList; // optional, SIZE(1..128)
    public RRC_vrb_ToPRB_Interleaver vrb_ToPRB_Interleaver; // optional
    public RRC_resourceAllocation_3 resourceAllocation; // mandatory
    public RRC_SetupRelease_PDSCH_TimeDomainResourceAllocationList pdsch_TimeDomainAllocationList; // optional
    public RRC_pdsch_AggregationFactor pdsch_AggregationFactor; // optional
    public RRC_rateMatchPatternToAddModList_3 rateMatchPatternToAddModList; // optional, SIZE(1..4)
    public RRC_rateMatchPatternToReleaseList_3 rateMatchPatternToReleaseList; // optional, SIZE(1..4)
    public RRC_RateMatchPatternGroup rateMatchPatternGroup1; // optional
    public RRC_RateMatchPatternGroup rateMatchPatternGroup2; // optional
    public RRC_rbg_Size_2 rbg_Size; // mandatory
    public RRC_mcs_Table_2 mcs_Table; // optional
    public RRC_maxNrofCodeWordsScheduledByDCI maxNrofCodeWordsScheduledByDCI; // optional
    public RRC_prb_BundlingType prb_BundlingType; // mandatory
    public RRC_zp_CSI_RS_ResourceToAddModList zp_CSI_RS_ResourceToAddModList; // optional, SIZE(1..32)
    public RRC_zp_CSI_RS_ResourceToReleaseList zp_CSI_RS_ResourceToReleaseList; // optional, SIZE(1..32)
    public RRC_aperiodic_ZP_CSI_RS_ResourceSetsToAddModList aperiodic_ZP_CSI_RS_ResourceSetsToAddModList; // optional, SIZE(1..16)
    public RRC_aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList; // optional, SIZE(1..16)
    public RRC_sp_ZP_CSI_RS_ResourceSetsToAddModList sp_ZP_CSI_RS_ResourceSetsToAddModList; // optional, SIZE(1..16)
    public RRC_sp_ZP_CSI_RS_ResourceSetsToReleaseList sp_ZP_CSI_RS_ResourceSetsToReleaseList; // optional, SIZE(1..16)
    public RRC_SetupRelease_ZP_CSI_RS_ResourceSet p_ZP_CSI_RS_ResourceSet; // optional

    public static class RRC_pdsch_AggregationFactor extends AsnEnumerated {
        public static final RRC_pdsch_AggregationFactor N2 = new RRC_pdsch_AggregationFactor(0);
        public static final RRC_pdsch_AggregationFactor N4 = new RRC_pdsch_AggregationFactor(1);
        public static final RRC_pdsch_AggregationFactor N8 = new RRC_pdsch_AggregationFactor(2);
    
        private RRC_pdsch_AggregationFactor(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_zp_CSI_RS_ResourceToReleaseList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceId> {
    }

    public static class RRC_vrb_ToPRB_Interleaver extends AsnEnumerated {
        public static final RRC_vrb_ToPRB_Interleaver N2 = new RRC_vrb_ToPRB_Interleaver(0);
        public static final RRC_vrb_ToPRB_Interleaver N4 = new RRC_vrb_ToPRB_Interleaver(1);
    
        private RRC_vrb_ToPRB_Interleaver(long value) {
            super(value);
        }
    }

    // SIZE(1..16)
    public static class RRC_aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceSetId> {
    }

    public static class RRC_prb_BundlingType extends AsnChoice {
        public RRC_staticBundling staticBundling;
        public RRC_dynamicBundling dynamicBundling;
    
        public static class RRC_dynamicBundling extends AsnSequence {
            public RRC_bundleSizeSet1 bundleSizeSet1; // optional
            public RRC_bundleSizeSet2 bundleSizeSet2; // optional
        
            public static class RRC_bundleSizeSet1 extends AsnEnumerated {
                public static final RRC_bundleSizeSet1 N4 = new RRC_bundleSizeSet1(0);
                public static final RRC_bundleSizeSet1 WIDEBAND = new RRC_bundleSizeSet1(1);
                public static final RRC_bundleSizeSet1 N2_WIDEBAND = new RRC_bundleSizeSet1(2);
                public static final RRC_bundleSizeSet1 N4_WIDEBAND = new RRC_bundleSizeSet1(3);
            
                private RRC_bundleSizeSet1(long value) {
                    super(value);
                }
            }
        
            public static class RRC_bundleSizeSet2 extends AsnEnumerated {
                public static final RRC_bundleSizeSet2 N4 = new RRC_bundleSizeSet2(0);
                public static final RRC_bundleSizeSet2 WIDEBAND = new RRC_bundleSizeSet2(1);
            
                private RRC_bundleSizeSet2(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_staticBundling extends AsnSequence {
            public RRC_bundleSize bundleSize; // optional
        
            public static class RRC_bundleSize extends AsnEnumerated {
                public static final RRC_bundleSize N4 = new RRC_bundleSize(0);
                public static final RRC_bundleSize WIDEBAND = new RRC_bundleSize(1);
            
                private RRC_bundleSize(long value) {
                    super(value);
                }
            }
        }
    }

    // SIZE(1..128)
    public static class RRC_tci_StatesToReleaseList extends AsnSequenceOf<RRC_TCI_StateId> {
    }

    // SIZE(1..16)
    public static class RRC_sp_ZP_CSI_RS_ResourceSetsToReleaseList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceSetId> {
    }

    public static class RRC_resourceAllocation_3 extends AsnEnumerated {
        public static final RRC_resourceAllocation_3 RESOURCEALLOCATIONTYPE0 = new RRC_resourceAllocation_3(0);
        public static final RRC_resourceAllocation_3 RESOURCEALLOCATIONTYPE1 = new RRC_resourceAllocation_3(1);
        public static final RRC_resourceAllocation_3 DYNAMICSWITCH = new RRC_resourceAllocation_3(2);
    
        private RRC_resourceAllocation_3(long value) {
            super(value);
        }
    }

    public static class RRC_maxNrofCodeWordsScheduledByDCI extends AsnEnumerated {
        public static final RRC_maxNrofCodeWordsScheduledByDCI N1 = new RRC_maxNrofCodeWordsScheduledByDCI(0);
        public static final RRC_maxNrofCodeWordsScheduledByDCI N2 = new RRC_maxNrofCodeWordsScheduledByDCI(1);
    
        private RRC_maxNrofCodeWordsScheduledByDCI(long value) {
            super(value);
        }
    }

    // SIZE(1..128)
    public static class RRC_tci_StatesToAddModList extends AsnSequenceOf<RRC_TCI_State> {
    }

    // SIZE(1..4)
    public static class RRC_rateMatchPatternToAddModList_3 extends AsnSequenceOf<RRC_RateMatchPattern> {
    }

    public static class RRC_rbg_Size_2 extends AsnEnumerated {
        public static final RRC_rbg_Size_2 CONFIG1 = new RRC_rbg_Size_2(0);
        public static final RRC_rbg_Size_2 CONFIG2 = new RRC_rbg_Size_2(1);
    
        private RRC_rbg_Size_2(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_Table_2 extends AsnEnumerated {
        public static final RRC_mcs_Table_2 QAM256 = new RRC_mcs_Table_2(0);
        public static final RRC_mcs_Table_2 QAM64LOWSE = new RRC_mcs_Table_2(1);
    
        private RRC_mcs_Table_2(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_zp_CSI_RS_ResourceToAddModList extends AsnSequenceOf<RRC_ZP_CSI_RS_Resource> {
    }

    // SIZE(1..16)
    public static class RRC_aperiodic_ZP_CSI_RS_ResourceSetsToAddModList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceSet> {
    }

    // SIZE(1..16)
    public static class RRC_sp_ZP_CSI_RS_ResourceSetsToAddModList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceSet> {
    }

    public static class RRC_SetupRelease_ZP_CSI_RS_ResourceSet extends AsnChoice {
        public AsnNull release;
        public RRC_ZP_CSI_RS_ResourceSet setup;
    }

    public static class RRC_SetupRelease_PDSCH_TimeDomainResourceAllocationList extends AsnChoice {
        public AsnNull release;
        public RRC_PDSCH_TimeDomainResourceAllocationList setup;
    }

    // SIZE(1..4)
    public static class RRC_rateMatchPatternToReleaseList_3 extends AsnSequenceOf<RRC_RateMatchPatternId> {
    }
}


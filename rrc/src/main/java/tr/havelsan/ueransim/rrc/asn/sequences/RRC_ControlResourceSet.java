/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_ControlResourceSet extends AsnSequence {
    public RRC_ControlResourceSetId controlResourceSetId; // mandatory
    public AsnBitString frequencyDomainResources; // mandatory, SIZE(45)
    public AsnInteger duration; // mandatory, VALUE(1..3)
    public RRC_cce_REG_MappingType cce_REG_MappingType; // mandatory
    public RRC_precoderGranularity precoderGranularity; // mandatory
    public RRC_tci_StatesPDCCH_ToAddList tci_StatesPDCCH_ToAddList; // optional, SIZE(1..64)
    public RRC_tci_StatesPDCCH_ToReleaseList tci_StatesPDCCH_ToReleaseList; // optional, SIZE(1..64)
    public RRC_tci_PresentInDCI tci_PresentInDCI; // optional
    public AsnInteger pdcch_DMRS_ScramblingID; // optional, VALUE(0..65535)

    public static class RRC_tci_PresentInDCI extends AsnEnumerated {
        public static final RRC_tci_PresentInDCI ENABLED = new RRC_tci_PresentInDCI(0);
    
        private RRC_tci_PresentInDCI(long value) {
            super(value);
        }
    }

    // SIZE(1..64)
    public static class RRC_tci_StatesPDCCH_ToReleaseList extends AsnSequenceOf<RRC_TCI_StateId> {
    }

    // SIZE(1..64)
    public static class RRC_tci_StatesPDCCH_ToAddList extends AsnSequenceOf<RRC_TCI_StateId> {
    }

    public static class RRC_precoderGranularity extends AsnEnumerated {
        public static final RRC_precoderGranularity SAMEASREG_BUNDLE = new RRC_precoderGranularity(0);
        public static final RRC_precoderGranularity ALLCONTIGUOUSRBS = new RRC_precoderGranularity(1);
    
        private RRC_precoderGranularity(long value) {
            super(value);
        }
    }

    public static class RRC_cce_REG_MappingType extends AsnChoice {
        public RRC_interleaved interleaved;
        public AsnNull nonInterleaved;
    
        public static class RRC_interleaved extends AsnSequence {
            public RRC_reg_BundleSize reg_BundleSize; // mandatory
            public RRC_interleaverSize interleaverSize; // mandatory
            public AsnInteger shiftIndex; // optional, VALUE(0..274)
        
            public static class RRC_reg_BundleSize extends AsnEnumerated {
                public static final RRC_reg_BundleSize N2 = new RRC_reg_BundleSize(0);
                public static final RRC_reg_BundleSize N3 = new RRC_reg_BundleSize(1);
                public static final RRC_reg_BundleSize N6 = new RRC_reg_BundleSize(2);
            
                private RRC_reg_BundleSize(long value) {
                    super(value);
                }
            }
        
            public static class RRC_interleaverSize extends AsnEnumerated {
                public static final RRC_interleaverSize N2 = new RRC_interleaverSize(0);
                public static final RRC_interleaverSize N3 = new RRC_interleaverSize(1);
                public static final RRC_interleaverSize N6 = new RRC_interleaverSize(2);
            
                private RRC_interleaverSize(long value) {
                    super(value);
                }
            }
        }
    }
}


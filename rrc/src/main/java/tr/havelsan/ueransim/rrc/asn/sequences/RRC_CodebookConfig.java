/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_CodebookConfig extends AsnSequence {
    public RRC_codebookType codebookType; // mandatory

    public static class RRC_codebookType extends AsnChoice {
        public RRC_type1_2 type1;
        public RRC_type2_1 type2;
    
        public static class RRC_type2_1 extends AsnSequence {
            public RRC_subType_2 subType; // mandatory
            public RRC_phaseAlphabetSize phaseAlphabetSize; // mandatory
            public AsnBoolean subbandAmplitude; // mandatory
            public RRC_numberOfBeams numberOfBeams; // mandatory
        
            public static class RRC_numberOfBeams extends AsnEnumerated {
                public static final RRC_numberOfBeams TWO = new RRC_numberOfBeams(0);
                public static final RRC_numberOfBeams THREE = new RRC_numberOfBeams(1);
                public static final RRC_numberOfBeams FOUR = new RRC_numberOfBeams(2);
            
                private RRC_numberOfBeams(long value) {
                    super(value);
                }
            }
        
            public static class RRC_subType_2 extends AsnChoice {
                public RRC_typeII typeII;
                public RRC_typeII_PortSelection typeII_PortSelection;
            
                public static class RRC_typeII extends AsnSequence {
                    public RRC_n1_n2_codebookSubsetRestriction n1_n2_codebookSubsetRestriction; // mandatory
                    public AsnBitString typeII_RI_Restriction; // mandatory, SIZE(2)
                
                    public static class RRC_n1_n2_codebookSubsetRestriction extends AsnChoice {
                        public AsnBitString two_one; // SIZE(16)
                        public AsnBitString two_two; // SIZE(43)
                        public AsnBitString four_one; // SIZE(32)
                        public AsnBitString three_two; // SIZE(59)
                        public AsnBitString six_one; // SIZE(48)
                        public AsnBitString four_two; // SIZE(75)
                        public AsnBitString eight_one; // SIZE(64)
                        public AsnBitString four_three; // SIZE(107)
                        public AsnBitString six_two; // SIZE(107)
                        public AsnBitString twelve_one; // SIZE(96)
                        public AsnBitString four_four; // SIZE(139)
                        public AsnBitString eight_two; // SIZE(139)
                        public AsnBitString sixteen_one; // SIZE(128)
                    }
                }
            
                public static class RRC_typeII_PortSelection extends AsnSequence {
                    public RRC_portSelectionSamplingSize portSelectionSamplingSize; // optional
                    public AsnBitString typeII_PortSelectionRI_Restriction; // mandatory, SIZE(2)
                
                    public static class RRC_portSelectionSamplingSize extends AsnEnumerated {
                        public static final RRC_portSelectionSamplingSize N1 = new RRC_portSelectionSamplingSize(0);
                        public static final RRC_portSelectionSamplingSize N2 = new RRC_portSelectionSamplingSize(1);
                        public static final RRC_portSelectionSamplingSize N3 = new RRC_portSelectionSamplingSize(2);
                        public static final RRC_portSelectionSamplingSize N4 = new RRC_portSelectionSamplingSize(3);
                    
                        private RRC_portSelectionSamplingSize(long value) {
                            super(value);
                        }
                    }
                }
            }
        
            public static class RRC_phaseAlphabetSize extends AsnEnumerated {
                public static final RRC_phaseAlphabetSize N4 = new RRC_phaseAlphabetSize(0);
                public static final RRC_phaseAlphabetSize N8 = new RRC_phaseAlphabetSize(1);
            
                private RRC_phaseAlphabetSize(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_type1_2 extends AsnSequence {
            public RRC_subType_1 subType; // mandatory
            public AsnInteger codebookMode; // mandatory, VALUE(1..2)
        
            public static class RRC_subType_1 extends AsnChoice {
                public RRC_typeI_SinglePanel typeI_SinglePanel;
                public RRC_typeI_MultiPanel typeI_MultiPanel;
            
                public static class RRC_typeI_SinglePanel extends AsnSequence {
                    public RRC_nrOfAntennaPorts nrOfAntennaPorts; // mandatory
                    public AsnBitString typeI_SinglePanel_ri_Restriction; // mandatory, SIZE(8)
                
                    public static class RRC_nrOfAntennaPorts extends AsnChoice {
                        public RRC_two_2 two;
                        public RRC_moreThanTwo moreThanTwo;
                    
                        public static class RRC_two_2 extends AsnSequence {
                            public AsnBitString twoTX_CodebookSubsetRestriction; // mandatory, SIZE(6)
                        }
                    
                        public static class RRC_moreThanTwo extends AsnSequence {
                            public RRC_n1_n2 n1_n2; // mandatory
                            public AsnBitString typeI_SinglePanel_codebookSubsetRestriction_i2; // optional, SIZE(16)
                        
                            public static class RRC_n1_n2 extends AsnChoice {
                                public AsnBitString two_one_TypeI_SinglePanel_Restriction; // SIZE(8)
                                public AsnBitString two_two_TypeI_SinglePanel_Restriction; // SIZE(64)
                                public AsnBitString four_one_TypeI_SinglePanel_Restriction; // SIZE(16)
                                public AsnBitString three_two_TypeI_SinglePanel_Restriction; // SIZE(96)
                                public AsnBitString six_one_TypeI_SinglePanel_Restriction; // SIZE(24)
                                public AsnBitString four_two_TypeI_SinglePanel_Restriction; // SIZE(128)
                                public AsnBitString eight_one_TypeI_SinglePanel_Restriction; // SIZE(32)
                                public AsnBitString four_three_TypeI_SinglePanel_Restriction; // SIZE(192)
                                public AsnBitString six_two_TypeI_SinglePanel_Restriction; // SIZE(192)
                                public AsnBitString twelve_one_TypeI_SinglePanel_Restriction; // SIZE(48)
                                public AsnBitString four_four_TypeI_SinglePanel_Restriction; // SIZE(256)
                                public AsnBitString eight_two_TypeI_SinglePanel_Restriction; // SIZE(256)
                                public AsnBitString sixteen_one_TypeI_SinglePanel_Restriction; // SIZE(64)
                            }
                        }
                    }
                }
            
                public static class RRC_typeI_MultiPanel extends AsnSequence {
                    public RRC_ng_n1_n2 ng_n1_n2; // mandatory
                    public AsnBitString ri_Restriction; // mandatory, SIZE(4)
                
                    public static class RRC_ng_n1_n2 extends AsnChoice {
                        public AsnBitString two_two_one_TypeI_MultiPanel_Restriction; // SIZE(8)
                        public AsnBitString two_four_one_TypeI_MultiPanel_Restriction; // SIZE(16)
                        public AsnBitString four_two_one_TypeI_MultiPanel_Restriction; // SIZE(8)
                        public AsnBitString two_two_two_TypeI_MultiPanel_Restriction; // SIZE(64)
                        public AsnBitString two_eight_one_TypeI_MultiPanel_Restriction; // SIZE(32)
                        public AsnBitString four_four_one_TypeI_MultiPanel_Restriction; // SIZE(16)
                        public AsnBitString two_four_two_TypeI_MultiPanel_Restriction; // SIZE(128)
                        public AsnBitString four_two_two_TypeI_MultiPanel_Restriction; // SIZE(64)
                    }
                }
            }
        }
    }
}


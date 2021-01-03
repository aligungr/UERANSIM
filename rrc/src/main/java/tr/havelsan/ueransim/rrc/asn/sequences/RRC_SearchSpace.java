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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;

public class RRC_SearchSpace extends AsnSequence {
    public RRC_SearchSpaceId searchSpaceId; // mandatory
    public RRC_ControlResourceSetId controlResourceSetId; // optional
    public RRC_monitoringSlotPeriodicityAndOffset monitoringSlotPeriodicityAndOffset; // optional
    public AsnInteger duration; // optional, VALUE(2..2559)
    public AsnBitString monitoringSymbolsWithinSlot; // optional, SIZE(14)
    public RRC_nrofCandidates nrofCandidates; // optional
    public RRC_searchSpaceType searchSpaceType; // optional

    public static class RRC_nrofCandidates extends AsnSequence {
        public RRC_aggregationLevel1_2 aggregationLevel1; // mandatory
        public RRC_aggregationLevel2_1 aggregationLevel2; // mandatory
        public RRC_aggregationLevel4_2 aggregationLevel4; // mandatory
        public RRC_aggregationLevel8_2 aggregationLevel8; // mandatory
        public RRC_aggregationLevel16_2 aggregationLevel16; // mandatory
    
        public static class RRC_aggregationLevel2_1 extends AsnEnumerated {
            public static final RRC_aggregationLevel2_1 N0 = new RRC_aggregationLevel2_1(0);
            public static final RRC_aggregationLevel2_1 N1 = new RRC_aggregationLevel2_1(1);
            public static final RRC_aggregationLevel2_1 N2 = new RRC_aggregationLevel2_1(2);
            public static final RRC_aggregationLevel2_1 N3 = new RRC_aggregationLevel2_1(3);
            public static final RRC_aggregationLevel2_1 N4 = new RRC_aggregationLevel2_1(4);
            public static final RRC_aggregationLevel2_1 N5 = new RRC_aggregationLevel2_1(5);
            public static final RRC_aggregationLevel2_1 N6 = new RRC_aggregationLevel2_1(6);
            public static final RRC_aggregationLevel2_1 N8 = new RRC_aggregationLevel2_1(7);
        
            private RRC_aggregationLevel2_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_aggregationLevel1_2 extends AsnEnumerated {
            public static final RRC_aggregationLevel1_2 N0 = new RRC_aggregationLevel1_2(0);
            public static final RRC_aggregationLevel1_2 N1 = new RRC_aggregationLevel1_2(1);
            public static final RRC_aggregationLevel1_2 N2 = new RRC_aggregationLevel1_2(2);
            public static final RRC_aggregationLevel1_2 N3 = new RRC_aggregationLevel1_2(3);
            public static final RRC_aggregationLevel1_2 N4 = new RRC_aggregationLevel1_2(4);
            public static final RRC_aggregationLevel1_2 N5 = new RRC_aggregationLevel1_2(5);
            public static final RRC_aggregationLevel1_2 N6 = new RRC_aggregationLevel1_2(6);
            public static final RRC_aggregationLevel1_2 N8 = new RRC_aggregationLevel1_2(7);
        
            private RRC_aggregationLevel1_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_aggregationLevel8_2 extends AsnEnumerated {
            public static final RRC_aggregationLevel8_2 N0 = new RRC_aggregationLevel8_2(0);
            public static final RRC_aggregationLevel8_2 N1 = new RRC_aggregationLevel8_2(1);
            public static final RRC_aggregationLevel8_2 N2 = new RRC_aggregationLevel8_2(2);
            public static final RRC_aggregationLevel8_2 N3 = new RRC_aggregationLevel8_2(3);
            public static final RRC_aggregationLevel8_2 N4 = new RRC_aggregationLevel8_2(4);
            public static final RRC_aggregationLevel8_2 N5 = new RRC_aggregationLevel8_2(5);
            public static final RRC_aggregationLevel8_2 N6 = new RRC_aggregationLevel8_2(6);
            public static final RRC_aggregationLevel8_2 N8 = new RRC_aggregationLevel8_2(7);
        
            private RRC_aggregationLevel8_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_aggregationLevel4_2 extends AsnEnumerated {
            public static final RRC_aggregationLevel4_2 N0 = new RRC_aggregationLevel4_2(0);
            public static final RRC_aggregationLevel4_2 N1 = new RRC_aggregationLevel4_2(1);
            public static final RRC_aggregationLevel4_2 N2 = new RRC_aggregationLevel4_2(2);
            public static final RRC_aggregationLevel4_2 N3 = new RRC_aggregationLevel4_2(3);
            public static final RRC_aggregationLevel4_2 N4 = new RRC_aggregationLevel4_2(4);
            public static final RRC_aggregationLevel4_2 N5 = new RRC_aggregationLevel4_2(5);
            public static final RRC_aggregationLevel4_2 N6 = new RRC_aggregationLevel4_2(6);
            public static final RRC_aggregationLevel4_2 N8 = new RRC_aggregationLevel4_2(7);
        
            private RRC_aggregationLevel4_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_aggregationLevel16_2 extends AsnEnumerated {
            public static final RRC_aggregationLevel16_2 N0 = new RRC_aggregationLevel16_2(0);
            public static final RRC_aggregationLevel16_2 N1 = new RRC_aggregationLevel16_2(1);
            public static final RRC_aggregationLevel16_2 N2 = new RRC_aggregationLevel16_2(2);
            public static final RRC_aggregationLevel16_2 N3 = new RRC_aggregationLevel16_2(3);
            public static final RRC_aggregationLevel16_2 N4 = new RRC_aggregationLevel16_2(4);
            public static final RRC_aggregationLevel16_2 N5 = new RRC_aggregationLevel16_2(5);
            public static final RRC_aggregationLevel16_2 N6 = new RRC_aggregationLevel16_2(6);
            public static final RRC_aggregationLevel16_2 N8 = new RRC_aggregationLevel16_2(7);
        
            private RRC_aggregationLevel16_2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_monitoringSlotPeriodicityAndOffset extends AsnChoice {
        public AsnNull sl1;
        public AsnInteger sl2; // VALUE(0..1)
        public AsnInteger sl4; // VALUE(0..3)
        public AsnInteger sl5; // VALUE(0..4)
        public AsnInteger sl8; // VALUE(0..7)
        public AsnInteger sl10; // VALUE(0..9)
        public AsnInteger sl16; // VALUE(0..15)
        public AsnInteger sl20; // VALUE(0..19)
        public AsnInteger sl40; // VALUE(0..39)
        public AsnInteger sl80; // VALUE(0..79)
        public AsnInteger sl160; // VALUE(0..159)
        public AsnInteger sl320; // VALUE(0..319)
        public AsnInteger sl640; // VALUE(0..639)
        public AsnInteger sl1280; // VALUE(0..1279)
        public AsnInteger sl2560; // VALUE(0..2559)
    }

    public static class RRC_searchSpaceType extends AsnChoice {
        public RRC_common common;
        public RRC_ue_Specific ue_Specific;
    
        public static class RRC_ue_Specific extends AsnSequence {
            public RRC_dci_Formats dci_Formats; // mandatory
        
            public static class RRC_dci_Formats extends AsnEnumerated {
                public static final RRC_dci_Formats FORMATS0_0_AND_1_0 = new RRC_dci_Formats(0);
                public static final RRC_dci_Formats FORMATS0_1_AND_1_1 = new RRC_dci_Formats(1);
            
                private RRC_dci_Formats(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_common extends AsnSequence {
            public RRC_dci_Format0_0_AndFormat1_0 dci_Format0_0_AndFormat1_0; // optional
            public RRC_dci_Format2_0 dci_Format2_0; // optional
            public RRC_dci_Format2_1 dci_Format2_1; // optional
            public RRC_dci_Format2_2 dci_Format2_2; // optional
            public RRC_dci_Format2_3 dci_Format2_3; // optional
        
            public static class RRC_dci_Format2_3 extends AsnSequence {
                public RRC_dummy1_1 dummy1; // optional
                public RRC_dummy2_2 dummy2; // mandatory
            
                public static class RRC_dummy2_2 extends AsnEnumerated {
                    public static final RRC_dummy2_2 N1 = new RRC_dummy2_2(0);
                    public static final RRC_dummy2_2 N2 = new RRC_dummy2_2(1);
                
                    private RRC_dummy2_2(long value) {
                        super(value);
                    }
                }
            
                public static class RRC_dummy1_1 extends AsnEnumerated {
                    public static final RRC_dummy1_1 SL1 = new RRC_dummy1_1(0);
                    public static final RRC_dummy1_1 SL2 = new RRC_dummy1_1(1);
                    public static final RRC_dummy1_1 SL4 = new RRC_dummy1_1(2);
                    public static final RRC_dummy1_1 SL5 = new RRC_dummy1_1(3);
                    public static final RRC_dummy1_1 SL8 = new RRC_dummy1_1(4);
                    public static final RRC_dummy1_1 SL10 = new RRC_dummy1_1(5);
                    public static final RRC_dummy1_1 SL16 = new RRC_dummy1_1(6);
                    public static final RRC_dummy1_1 SL20 = new RRC_dummy1_1(7);
                
                    private RRC_dummy1_1(long value) {
                        super(value);
                    }
                }
            }
        
            public static class RRC_dci_Format2_1 extends AsnSequence {
            }
        
            public static class RRC_dci_Format0_0_AndFormat1_0 extends AsnSequence {
            }
        
            public static class RRC_dci_Format2_2 extends AsnSequence {
            }
        
            public static class RRC_dci_Format2_0 extends AsnSequence {
                public RRC_nrofCandidates_SFI nrofCandidates_SFI; // mandatory
            
                public static class RRC_nrofCandidates_SFI extends AsnSequence {
                    public RRC_aggregationLevel1_1 aggregationLevel1; // optional
                    public RRC_aggregationLevel2_2 aggregationLevel2; // optional
                    public RRC_aggregationLevel4_1 aggregationLevel4; // optional
                    public RRC_aggregationLevel8_1 aggregationLevel8; // optional
                    public RRC_aggregationLevel16_1 aggregationLevel16; // optional
                
                    public static class RRC_aggregationLevel8_1 extends AsnEnumerated {
                        public static final RRC_aggregationLevel8_1 N1 = new RRC_aggregationLevel8_1(0);
                        public static final RRC_aggregationLevel8_1 N2 = new RRC_aggregationLevel8_1(1);
                    
                        private RRC_aggregationLevel8_1(long value) {
                            super(value);
                        }
                    }
                
                    public static class RRC_aggregationLevel4_1 extends AsnEnumerated {
                        public static final RRC_aggregationLevel4_1 N1 = new RRC_aggregationLevel4_1(0);
                        public static final RRC_aggregationLevel4_1 N2 = new RRC_aggregationLevel4_1(1);
                    
                        private RRC_aggregationLevel4_1(long value) {
                            super(value);
                        }
                    }
                
                    public static class RRC_aggregationLevel1_1 extends AsnEnumerated {
                        public static final RRC_aggregationLevel1_1 N1 = new RRC_aggregationLevel1_1(0);
                        public static final RRC_aggregationLevel1_1 N2 = new RRC_aggregationLevel1_1(1);
                    
                        private RRC_aggregationLevel1_1(long value) {
                            super(value);
                        }
                    }
                
                    public static class RRC_aggregationLevel2_2 extends AsnEnumerated {
                        public static final RRC_aggregationLevel2_2 N1 = new RRC_aggregationLevel2_2(0);
                        public static final RRC_aggregationLevel2_2 N2 = new RRC_aggregationLevel2_2(1);
                    
                        private RRC_aggregationLevel2_2(long value) {
                            super(value);
                        }
                    }
                
                    public static class RRC_aggregationLevel16_1 extends AsnEnumerated {
                        public static final RRC_aggregationLevel16_1 N1 = new RRC_aggregationLevel16_1(0);
                        public static final RRC_aggregationLevel16_1 N2 = new RRC_aggregationLevel16_1(1);
                    
                        private RRC_aggregationLevel16_1(long value) {
                            super(value);
                        }
                    }
                }
            }
        }
    }
}


/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_UL_DataSplitThreshold;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;

public class RRC_PDCP_Config extends AsnSequence {
    public RRC_drb drb; // optional
    public RRC_moreThanOneRLC moreThanOneRLC; // optional
    public RRC_t_Reordering t_Reordering; // optional
    public RRC_ext1_30 ext1; // optional

    public static class RRC_drb extends AsnSequence {
        public RRC_discardTimer discardTimer; // optional
        public RRC_pdcp_SN_SizeUL pdcp_SN_SizeUL; // optional
        public RRC_pdcp_SN_SizeDL pdcp_SN_SizeDL; // optional
        public RRC_headerCompression headerCompression; // mandatory
        public RRC_integrityProtection integrityProtection; // optional
        public RRC_statusReportRequired statusReportRequired; // optional
        public RRC_outOfOrderDelivery_2 outOfOrderDelivery; // optional
    
        public static class RRC_outOfOrderDelivery_2 extends AsnEnumerated {
            public static final RRC_outOfOrderDelivery_2 TRUE = new RRC_outOfOrderDelivery_2(0);
        
            private RRC_outOfOrderDelivery_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_statusReportRequired extends AsnEnumerated {
            public static final RRC_statusReportRequired TRUE = new RRC_statusReportRequired(0);
        
            private RRC_statusReportRequired(long value) {
                super(value);
            }
        }
    
        public static class RRC_integrityProtection extends AsnEnumerated {
            public static final RRC_integrityProtection ENABLED = new RRC_integrityProtection(0);
        
            private RRC_integrityProtection(long value) {
                super(value);
            }
        }
    
        public static class RRC_pdcp_SN_SizeDL extends AsnEnumerated {
            public static final RRC_pdcp_SN_SizeDL LEN12BITS = new RRC_pdcp_SN_SizeDL(0);
            public static final RRC_pdcp_SN_SizeDL LEN18BITS = new RRC_pdcp_SN_SizeDL(1);
        
            private RRC_pdcp_SN_SizeDL(long value) {
                super(value);
            }
        }
    
        public static class RRC_pdcp_SN_SizeUL extends AsnEnumerated {
            public static final RRC_pdcp_SN_SizeUL LEN12BITS = new RRC_pdcp_SN_SizeUL(0);
            public static final RRC_pdcp_SN_SizeUL LEN18BITS = new RRC_pdcp_SN_SizeUL(1);
        
            private RRC_pdcp_SN_SizeUL(long value) {
                super(value);
            }
        }
    
        public static class RRC_discardTimer extends AsnEnumerated {
            public static final RRC_discardTimer MS10 = new RRC_discardTimer(0);
            public static final RRC_discardTimer MS20 = new RRC_discardTimer(1);
            public static final RRC_discardTimer MS30 = new RRC_discardTimer(2);
            public static final RRC_discardTimer MS40 = new RRC_discardTimer(3);
            public static final RRC_discardTimer MS50 = new RRC_discardTimer(4);
            public static final RRC_discardTimer MS60 = new RRC_discardTimer(5);
            public static final RRC_discardTimer MS75 = new RRC_discardTimer(6);
            public static final RRC_discardTimer MS100 = new RRC_discardTimer(7);
            public static final RRC_discardTimer MS150 = new RRC_discardTimer(8);
            public static final RRC_discardTimer MS200 = new RRC_discardTimer(9);
            public static final RRC_discardTimer MS250 = new RRC_discardTimer(10);
            public static final RRC_discardTimer MS300 = new RRC_discardTimer(11);
            public static final RRC_discardTimer MS500 = new RRC_discardTimer(12);
            public static final RRC_discardTimer MS750 = new RRC_discardTimer(13);
            public static final RRC_discardTimer MS1500 = new RRC_discardTimer(14);
            public static final RRC_discardTimer INFINITY = new RRC_discardTimer(15);
        
            private RRC_discardTimer(long value) {
                super(value);
            }
        }
    
        public static class RRC_headerCompression extends AsnChoice {
            public AsnNull notUsed;
            public RRC_rohc rohc;
            public RRC_uplinkOnlyROHC uplinkOnlyROHC;
        
            public static class RRC_rohc extends AsnSequence {
                public AsnInteger maxCID; // optional, VALUE(1..16383)
                public RRC_profiles_1 profiles; // mandatory
                public RRC_drb_ContinueROHC_1 drb_ContinueROHC; // optional
            
                public static class RRC_profiles_1 extends AsnSequence {
                    public AsnBoolean profile0x0001; // mandatory
                    public AsnBoolean profile0x0002; // mandatory
                    public AsnBoolean profile0x0003; // mandatory
                    public AsnBoolean profile0x0004; // mandatory
                    public AsnBoolean profile0x0006; // mandatory
                    public AsnBoolean profile0x0101; // mandatory
                    public AsnBoolean profile0x0102; // mandatory
                    public AsnBoolean profile0x0103; // mandatory
                    public AsnBoolean profile0x0104; // mandatory
                }
            
                public static class RRC_drb_ContinueROHC_1 extends AsnEnumerated {
                    public static final RRC_drb_ContinueROHC_1 TRUE = new RRC_drb_ContinueROHC_1(0);
                
                    private RRC_drb_ContinueROHC_1(long value) {
                        super(value);
                    }
                }
            }
        
            public static class RRC_uplinkOnlyROHC extends AsnSequence {
                public AsnInteger maxCID; // optional, VALUE(1..16383)
                public RRC_profiles_2 profiles; // mandatory
                public RRC_drb_ContinueROHC_2 drb_ContinueROHC; // optional
            
                public static class RRC_profiles_2 extends AsnSequence {
                    public AsnBoolean profile0x0006; // mandatory
                }
            
                public static class RRC_drb_ContinueROHC_2 extends AsnEnumerated {
                    public static final RRC_drb_ContinueROHC_2 TRUE = new RRC_drb_ContinueROHC_2(0);
                
                    private RRC_drb_ContinueROHC_2(long value) {
                        super(value);
                    }
                }
            }
        }
    }

    public static class RRC_ext1_30 extends AsnSequence {
        public RRC_cipheringDisabled cipheringDisabled; // optional
    
        public static class RRC_cipheringDisabled extends AsnEnumerated {
            public static final RRC_cipheringDisabled TRUE = new RRC_cipheringDisabled(0);
        
            private RRC_cipheringDisabled(long value) {
                super(value);
            }
        }
    }

    public static class RRC_t_Reordering extends AsnEnumerated {
        public static final RRC_t_Reordering MS0 = new RRC_t_Reordering(0);
        public static final RRC_t_Reordering MS1 = new RRC_t_Reordering(1);
        public static final RRC_t_Reordering MS2 = new RRC_t_Reordering(2);
        public static final RRC_t_Reordering MS4 = new RRC_t_Reordering(3);
        public static final RRC_t_Reordering MS5 = new RRC_t_Reordering(4);
        public static final RRC_t_Reordering MS8 = new RRC_t_Reordering(5);
        public static final RRC_t_Reordering MS10 = new RRC_t_Reordering(6);
        public static final RRC_t_Reordering MS15 = new RRC_t_Reordering(7);
        public static final RRC_t_Reordering MS20 = new RRC_t_Reordering(8);
        public static final RRC_t_Reordering MS30 = new RRC_t_Reordering(9);
        public static final RRC_t_Reordering MS40 = new RRC_t_Reordering(10);
        public static final RRC_t_Reordering MS50 = new RRC_t_Reordering(11);
        public static final RRC_t_Reordering MS60 = new RRC_t_Reordering(12);
        public static final RRC_t_Reordering MS80 = new RRC_t_Reordering(13);
        public static final RRC_t_Reordering MS100 = new RRC_t_Reordering(14);
        public static final RRC_t_Reordering MS120 = new RRC_t_Reordering(15);
        public static final RRC_t_Reordering MS140 = new RRC_t_Reordering(16);
        public static final RRC_t_Reordering MS160 = new RRC_t_Reordering(17);
        public static final RRC_t_Reordering MS180 = new RRC_t_Reordering(18);
        public static final RRC_t_Reordering MS200 = new RRC_t_Reordering(19);
        public static final RRC_t_Reordering MS220 = new RRC_t_Reordering(20);
        public static final RRC_t_Reordering MS240 = new RRC_t_Reordering(21);
        public static final RRC_t_Reordering MS260 = new RRC_t_Reordering(22);
        public static final RRC_t_Reordering MS280 = new RRC_t_Reordering(23);
        public static final RRC_t_Reordering MS300 = new RRC_t_Reordering(24);
        public static final RRC_t_Reordering MS500 = new RRC_t_Reordering(25);
        public static final RRC_t_Reordering MS750 = new RRC_t_Reordering(26);
        public static final RRC_t_Reordering MS1000 = new RRC_t_Reordering(27);
        public static final RRC_t_Reordering MS1250 = new RRC_t_Reordering(28);
        public static final RRC_t_Reordering MS1500 = new RRC_t_Reordering(29);
        public static final RRC_t_Reordering MS1750 = new RRC_t_Reordering(30);
        public static final RRC_t_Reordering MS2000 = new RRC_t_Reordering(31);
        public static final RRC_t_Reordering MS2250 = new RRC_t_Reordering(32);
        public static final RRC_t_Reordering MS2500 = new RRC_t_Reordering(33);
        public static final RRC_t_Reordering MS2750 = new RRC_t_Reordering(34);
        public static final RRC_t_Reordering MS3000 = new RRC_t_Reordering(35);
        public static final RRC_t_Reordering SPARE28 = new RRC_t_Reordering(36);
        public static final RRC_t_Reordering SPARE27 = new RRC_t_Reordering(37);
        public static final RRC_t_Reordering SPARE26 = new RRC_t_Reordering(38);
        public static final RRC_t_Reordering SPARE25 = new RRC_t_Reordering(39);
        public static final RRC_t_Reordering SPARE24 = new RRC_t_Reordering(40);
        public static final RRC_t_Reordering SPARE23 = new RRC_t_Reordering(41);
        public static final RRC_t_Reordering SPARE22 = new RRC_t_Reordering(42);
        public static final RRC_t_Reordering SPARE21 = new RRC_t_Reordering(43);
        public static final RRC_t_Reordering SPARE20 = new RRC_t_Reordering(44);
        public static final RRC_t_Reordering SPARE19 = new RRC_t_Reordering(45);
        public static final RRC_t_Reordering SPARE18 = new RRC_t_Reordering(46);
        public static final RRC_t_Reordering SPARE17 = new RRC_t_Reordering(47);
        public static final RRC_t_Reordering SPARE16 = new RRC_t_Reordering(48);
        public static final RRC_t_Reordering SPARE15 = new RRC_t_Reordering(49);
        public static final RRC_t_Reordering SPARE14 = new RRC_t_Reordering(50);
        public static final RRC_t_Reordering SPARE13 = new RRC_t_Reordering(51);
        public static final RRC_t_Reordering SPARE12 = new RRC_t_Reordering(52);
        public static final RRC_t_Reordering SPARE11 = new RRC_t_Reordering(53);
        public static final RRC_t_Reordering SPARE10 = new RRC_t_Reordering(54);
        public static final RRC_t_Reordering SPARE09 = new RRC_t_Reordering(55);
        public static final RRC_t_Reordering SPARE08 = new RRC_t_Reordering(56);
        public static final RRC_t_Reordering SPARE07 = new RRC_t_Reordering(57);
        public static final RRC_t_Reordering SPARE06 = new RRC_t_Reordering(58);
        public static final RRC_t_Reordering SPARE05 = new RRC_t_Reordering(59);
        public static final RRC_t_Reordering SPARE04 = new RRC_t_Reordering(60);
        public static final RRC_t_Reordering SPARE03 = new RRC_t_Reordering(61);
        public static final RRC_t_Reordering SPARE02 = new RRC_t_Reordering(62);
        public static final RRC_t_Reordering SPARE01 = new RRC_t_Reordering(63);
    
        private RRC_t_Reordering(long value) {
            super(value);
        }
    }

    public static class RRC_moreThanOneRLC extends AsnSequence {
        public RRC_primaryPath primaryPath; // mandatory
        public RRC_UL_DataSplitThreshold ul_DataSplitThreshold; // optional
        public AsnBoolean pdcp_Duplication; // optional
    
        public static class RRC_primaryPath extends AsnSequence {
            public RRC_CellGroupId cellGroup; // optional
            public RRC_LogicalChannelIdentity logicalChannel; // optional
        }
    }
}


/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PDCP_Parameters extends AsnSequence {
    public RRC_supportedROHC_Profiles supportedROHC_Profiles; // mandatory
    public RRC_maxNumberROHC_ContextSessions maxNumberROHC_ContextSessions; // mandatory
    public RRC_uplinkOnlyROHC_Profiles uplinkOnlyROHC_Profiles; // optional
    public RRC_continueROHC_Context continueROHC_Context; // optional
    public RRC_outOfOrderDelivery_1 outOfOrderDelivery; // optional
    public RRC_shortSN shortSN; // optional
    public RRC_pdcp_DuplicationSRB pdcp_DuplicationSRB; // optional
    public RRC_pdcp_DuplicationMCG_OrSCG_DRB pdcp_DuplicationMCG_OrSCG_DRB; // optional

    public static class RRC_pdcp_DuplicationMCG_OrSCG_DRB extends AsnEnumerated {
        public static final RRC_pdcp_DuplicationMCG_OrSCG_DRB SUPPORTED = new RRC_pdcp_DuplicationMCG_OrSCG_DRB(0);
    
        private RRC_pdcp_DuplicationMCG_OrSCG_DRB(long value) {
            super(value);
        }
    }

    public static class RRC_pdcp_DuplicationSRB extends AsnEnumerated {
        public static final RRC_pdcp_DuplicationSRB SUPPORTED = new RRC_pdcp_DuplicationSRB(0);
    
        private RRC_pdcp_DuplicationSRB(long value) {
            super(value);
        }
    }

    public static class RRC_continueROHC_Context extends AsnEnumerated {
        public static final RRC_continueROHC_Context SUPPORTED = new RRC_continueROHC_Context(0);
    
        private RRC_continueROHC_Context(long value) {
            super(value);
        }
    }

    public static class RRC_uplinkOnlyROHC_Profiles extends AsnEnumerated {
        public static final RRC_uplinkOnlyROHC_Profiles SUPPORTED = new RRC_uplinkOnlyROHC_Profiles(0);
    
        private RRC_uplinkOnlyROHC_Profiles(long value) {
            super(value);
        }
    }

    public static class RRC_shortSN extends AsnEnumerated {
        public static final RRC_shortSN SUPPORTED = new RRC_shortSN(0);
    
        private RRC_shortSN(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberROHC_ContextSessions extends AsnEnumerated {
        public static final RRC_maxNumberROHC_ContextSessions CS2 = new RRC_maxNumberROHC_ContextSessions(0);
        public static final RRC_maxNumberROHC_ContextSessions CS4 = new RRC_maxNumberROHC_ContextSessions(1);
        public static final RRC_maxNumberROHC_ContextSessions CS8 = new RRC_maxNumberROHC_ContextSessions(2);
        public static final RRC_maxNumberROHC_ContextSessions CS12 = new RRC_maxNumberROHC_ContextSessions(3);
        public static final RRC_maxNumberROHC_ContextSessions CS16 = new RRC_maxNumberROHC_ContextSessions(4);
        public static final RRC_maxNumberROHC_ContextSessions CS24 = new RRC_maxNumberROHC_ContextSessions(5);
        public static final RRC_maxNumberROHC_ContextSessions CS32 = new RRC_maxNumberROHC_ContextSessions(6);
        public static final RRC_maxNumberROHC_ContextSessions CS48 = new RRC_maxNumberROHC_ContextSessions(7);
        public static final RRC_maxNumberROHC_ContextSessions CS64 = new RRC_maxNumberROHC_ContextSessions(8);
        public static final RRC_maxNumberROHC_ContextSessions CS128 = new RRC_maxNumberROHC_ContextSessions(9);
        public static final RRC_maxNumberROHC_ContextSessions CS256 = new RRC_maxNumberROHC_ContextSessions(10);
        public static final RRC_maxNumberROHC_ContextSessions CS512 = new RRC_maxNumberROHC_ContextSessions(11);
        public static final RRC_maxNumberROHC_ContextSessions CS1024 = new RRC_maxNumberROHC_ContextSessions(12);
        public static final RRC_maxNumberROHC_ContextSessions CS16384 = new RRC_maxNumberROHC_ContextSessions(13);
        public static final RRC_maxNumberROHC_ContextSessions SPARE2 = new RRC_maxNumberROHC_ContextSessions(14);
        public static final RRC_maxNumberROHC_ContextSessions SPARE1 = new RRC_maxNumberROHC_ContextSessions(15);
    
        private RRC_maxNumberROHC_ContextSessions(long value) {
            super(value);
        }
    }

    public static class RRC_supportedROHC_Profiles extends AsnSequence {
        public AsnBoolean profile0x0000; // mandatory
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

    public static class RRC_outOfOrderDelivery_1 extends AsnEnumerated {
        public static final RRC_outOfOrderDelivery_1 SUPPORTED = new RRC_outOfOrderDelivery_1(0);
    
        private RRC_outOfOrderDelivery_1(long value) {
            super(value);
        }
    }
}


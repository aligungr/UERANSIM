/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_OtherConfig extends AsnSequence {
    public RRC_delayBudgetReportingConfig delayBudgetReportingConfig; // optional

    public static class RRC_delayBudgetReportingConfig extends AsnChoice {
        public AsnNull release;
        public RRC_setup setup;
    
        public static class RRC_setup extends AsnSequence {
            public RRC_delayBudgetReportingProhibitTimer delayBudgetReportingProhibitTimer; // mandatory
        
            public static class RRC_delayBudgetReportingProhibitTimer extends AsnEnumerated {
                public static final RRC_delayBudgetReportingProhibitTimer S0 = new RRC_delayBudgetReportingProhibitTimer(0);
                public static final RRC_delayBudgetReportingProhibitTimer S0DOT4 = new RRC_delayBudgetReportingProhibitTimer(1);
                public static final RRC_delayBudgetReportingProhibitTimer S0DOT8 = new RRC_delayBudgetReportingProhibitTimer(2);
                public static final RRC_delayBudgetReportingProhibitTimer S1DOT6 = new RRC_delayBudgetReportingProhibitTimer(3);
                public static final RRC_delayBudgetReportingProhibitTimer S3 = new RRC_delayBudgetReportingProhibitTimer(4);
                public static final RRC_delayBudgetReportingProhibitTimer S6 = new RRC_delayBudgetReportingProhibitTimer(5);
                public static final RRC_delayBudgetReportingProhibitTimer S12 = new RRC_delayBudgetReportingProhibitTimer(6);
                public static final RRC_delayBudgetReportingProhibitTimer S30 = new RRC_delayBudgetReportingProhibitTimer(7);
            
                private RRC_delayBudgetReportingProhibitTimer(long value) {
                    super(value);
                }
            }
        }
    }
}


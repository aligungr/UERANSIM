/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantity;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantityEUTRA;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_NR_RS_Type;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfigInterRAT extends AsnSequence {
    public RRC_eventId_1 eventId; // mandatory
    public RRC_NR_RS_Type rsType; // mandatory
    public RRC_ReportInterval reportInterval; // mandatory
    public RRC_reportAmount_4 reportAmount; // mandatory
    public RRC_MeasReportQuantity reportQuantity; // mandatory
    public AsnInteger maxReportCells; // mandatory, VALUE(1..8)

    public static class RRC_eventId_1 extends AsnChoice {
        public RRC_eventB1 eventB1;
        public RRC_eventB2 eventB2;
    
        public static class RRC_eventB2 extends AsnSequence {
            public RRC_MeasTriggerQuantity b2_Threshold1; // mandatory
            public RRC_MeasTriggerQuantityEUTRA b2_Threshold2EUTRA; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
        }
    
        public static class RRC_eventB1 extends AsnSequence {
            public RRC_MeasTriggerQuantityEUTRA b1_ThresholdEUTRA; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
        }
    }

    public static class RRC_reportAmount_4 extends AsnEnumerated {
        public static final RRC_reportAmount_4 R1 = new RRC_reportAmount_4(0);
        public static final RRC_reportAmount_4 R2 = new RRC_reportAmount_4(1);
        public static final RRC_reportAmount_4 R4 = new RRC_reportAmount_4(2);
        public static final RRC_reportAmount_4 R8 = new RRC_reportAmount_4(3);
        public static final RRC_reportAmount_4 R16 = new RRC_reportAmount_4(4);
        public static final RRC_reportAmount_4 R32 = new RRC_reportAmount_4(5);
        public static final RRC_reportAmount_4 R64 = new RRC_reportAmount_4(6);
        public static final RRC_reportAmount_4 INFINITY = new RRC_reportAmount_4(7);
    
        private RRC_reportAmount_4(long value) {
            super(value);
        }
    }
}


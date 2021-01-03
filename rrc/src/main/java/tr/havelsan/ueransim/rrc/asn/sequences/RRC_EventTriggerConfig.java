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
import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantityOffset;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_NR_RS_Type;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfig extends AsnSequence {
    public RRC_eventId_2 eventId; // mandatory
    public RRC_NR_RS_Type rsType; // mandatory
    public RRC_ReportInterval reportInterval; // mandatory
    public RRC_reportAmount_2 reportAmount; // mandatory
    public RRC_MeasReportQuantity reportQuantityCell; // mandatory
    public AsnInteger maxReportCells; // mandatory, VALUE(1..8)
    public RRC_MeasReportQuantity reportQuantityRS_Indexes; // optional
    public AsnInteger maxNrofRS_IndexesToReport; // optional, VALUE(1..32)
    public AsnBoolean includeBeamMeasurements; // mandatory
    public RRC_reportAddNeighMeas reportAddNeighMeas; // optional

    public static class RRC_reportAddNeighMeas extends AsnEnumerated {
        public static final RRC_reportAddNeighMeas SETUP = new RRC_reportAddNeighMeas(0);
    
        private RRC_reportAddNeighMeas(long value) {
            super(value);
        }
    }

    public static class RRC_reportAmount_2 extends AsnEnumerated {
        public static final RRC_reportAmount_2 R1 = new RRC_reportAmount_2(0);
        public static final RRC_reportAmount_2 R2 = new RRC_reportAmount_2(1);
        public static final RRC_reportAmount_2 R4 = new RRC_reportAmount_2(2);
        public static final RRC_reportAmount_2 R8 = new RRC_reportAmount_2(3);
        public static final RRC_reportAmount_2 R16 = new RRC_reportAmount_2(4);
        public static final RRC_reportAmount_2 R32 = new RRC_reportAmount_2(5);
        public static final RRC_reportAmount_2 R64 = new RRC_reportAmount_2(6);
        public static final RRC_reportAmount_2 INFINITY = new RRC_reportAmount_2(7);
    
        private RRC_reportAmount_2(long value) {
            super(value);
        }
    }

    public static class RRC_eventId_2 extends AsnChoice {
        public RRC_eventA1 eventA1;
        public RRC_eventA2 eventA2;
        public RRC_eventA3 eventA3;
        public RRC_eventA4 eventA4;
        public RRC_eventA5 eventA5;
        public RRC_eventA6 eventA6;
    
        public static class RRC_eventA6 extends AsnSequence {
            public RRC_MeasTriggerQuantityOffset a6_Offset; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
            public AsnBoolean useWhiteCellList; // mandatory
        }
    
        public static class RRC_eventA4 extends AsnSequence {
            public RRC_MeasTriggerQuantity a4_Threshold; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
            public AsnBoolean useWhiteCellList; // mandatory
        }
    
        public static class RRC_eventA3 extends AsnSequence {
            public RRC_MeasTriggerQuantityOffset a3_Offset; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
            public AsnBoolean useWhiteCellList; // mandatory
        }
    
        public static class RRC_eventA1 extends AsnSequence {
            public RRC_MeasTriggerQuantity a1_Threshold; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
        }
    
        public static class RRC_eventA2 extends AsnSequence {
            public RRC_MeasTriggerQuantity a2_Threshold; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
        }
    
        public static class RRC_eventA5 extends AsnSequence {
            public RRC_MeasTriggerQuantity a5_Threshold1; // mandatory
            public RRC_MeasTriggerQuantity a5_Threshold2; // mandatory
            public AsnBoolean reportOnLeave; // mandatory
            public RRC_Hysteresis hysteresis; // mandatory
            public RRC_TimeToTrigger timeToTrigger; // mandatory
            public AsnBoolean useWhiteCellList; // mandatory
        }
    }
}


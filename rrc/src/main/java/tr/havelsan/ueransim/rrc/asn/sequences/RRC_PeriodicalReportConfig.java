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
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_NR_RS_Type;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;

public class RRC_PeriodicalReportConfig extends AsnSequence {
    public RRC_NR_RS_Type rsType; // mandatory
    public RRC_ReportInterval reportInterval; // mandatory
    public RRC_reportAmount_3 reportAmount; // mandatory
    public RRC_MeasReportQuantity reportQuantityCell; // mandatory
    public AsnInteger maxReportCells; // mandatory, VALUE(1..8)
    public RRC_MeasReportQuantity reportQuantityRS_Indexes; // optional
    public AsnInteger maxNrofRS_IndexesToReport; // optional, VALUE(1..32)
    public AsnBoolean includeBeamMeasurements; // mandatory
    public AsnBoolean useWhiteCellList; // mandatory

    public static class RRC_reportAmount_3 extends AsnEnumerated {
        public static final RRC_reportAmount_3 R1 = new RRC_reportAmount_3(0);
        public static final RRC_reportAmount_3 R2 = new RRC_reportAmount_3(1);
        public static final RRC_reportAmount_3 R4 = new RRC_reportAmount_3(2);
        public static final RRC_reportAmount_3 R8 = new RRC_reportAmount_3(3);
        public static final RRC_reportAmount_3 R16 = new RRC_reportAmount_3(4);
        public static final RRC_reportAmount_3 R32 = new RRC_reportAmount_3(5);
        public static final RRC_reportAmount_3 R64 = new RRC_reportAmount_3(6);
        public static final RRC_reportAmount_3 INFINITY = new RRC_reportAmount_3(7);
    
        private RRC_reportAmount_3(long value) {
            super(value);
        }
    }
}


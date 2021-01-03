/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;

public class RRC_PeriodicalReportConfigInterRAT extends AsnSequence {
    public RRC_ReportInterval reportInterval; // mandatory
    public RRC_reportAmount_1 reportAmount; // mandatory
    public RRC_MeasReportQuantity reportQuantity; // mandatory
    public AsnInteger maxReportCells; // mandatory, VALUE(1..8)

    public static class RRC_reportAmount_1 extends AsnEnumerated {
        public static final RRC_reportAmount_1 R1 = new RRC_reportAmount_1(0);
        public static final RRC_reportAmount_1 R2 = new RRC_reportAmount_1(1);
        public static final RRC_reportAmount_1 R4 = new RRC_reportAmount_1(2);
        public static final RRC_reportAmount_1 R8 = new RRC_reportAmount_1(3);
        public static final RRC_reportAmount_1 R16 = new RRC_reportAmount_1(4);
        public static final RRC_reportAmount_1 R32 = new RRC_reportAmount_1(5);
        public static final RRC_reportAmount_1 R64 = new RRC_reportAmount_1(6);
        public static final RRC_reportAmount_1 INFINITY = new RRC_reportAmount_1(7);
    
        private RRC_reportAmount_1(long value) {
            super(value);
        }
    }
}


/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;

public class RRC_PeriodicalReportConfigInterRAT extends RRC_Sequence {

    public RRC_ReportInterval reportInterval;
    public RRC_Integer reportAmount;
    public RRC_MeasReportQuantity reportQuantity;
    public RRC_Integer maxReportCells;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportInterval","reportAmount","reportQuantity","maxReportCells" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportInterval","reportAmount","reportQuantity","maxReportCells" };
    }

    @Override
    public String getAsnName() {
        return "PeriodicalReportConfigInterRAT";
    }

    @Override
    public String getXmlTagName() {
        return "PeriodicalReportConfigInterRAT";
    }

}

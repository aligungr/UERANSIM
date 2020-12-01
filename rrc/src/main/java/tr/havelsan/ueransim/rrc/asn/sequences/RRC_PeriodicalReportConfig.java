/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_NR_RS_Type;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReportInterval;

public class RRC_PeriodicalReportConfig extends RRC_Sequence {

    public RRC_NR_RS_Type rsType;
    public RRC_ReportInterval reportInterval;
    public RRC_Integer reportAmount;
    public RRC_MeasReportQuantity reportQuantityCell;
    public RRC_Integer maxReportCells;
    public RRC_MeasReportQuantity reportQuantityRS_Indexes;
    public RRC_Integer maxNrofRS_IndexesToReport;
    public RRC_Boolean includeBeamMeasurements;
    public RRC_Boolean useWhiteCellList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rsType","reportInterval","reportAmount","reportQuantityCell","maxReportCells","reportQuantityRS-Indexes","maxNrofRS-IndexesToReport","includeBeamMeasurements","useWhiteCellList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rsType","reportInterval","reportAmount","reportQuantityCell","maxReportCells","reportQuantityRS_Indexes","maxNrofRS_IndexesToReport","includeBeamMeasurements","useWhiteCellList" };
    }

    @Override
    public String getAsnName() {
        return "PeriodicalReportConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PeriodicalReportConfig";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_VarMeasReport;

public class RRC_VarMeasReportList extends RRC_SequenceOf<RRC_VarMeasReport> {

    @Override
    public String getAsnName() {
        return "VarMeasReportList";
    }

    @Override
    public String getXmlTagName() {
        return "VarMeasReportList";
    }

    @Override
    public Class<RRC_VarMeasReport> getItemType() {
        return RRC_VarMeasReport.class;
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellsTriggeredList;

public class RRC_VarMeasReport extends RRC_Sequence {

    public RRC_MeasId measId;
    public RRC_CellsTriggeredList cellsTriggeredList;
    public RRC_Integer numberOfReportsSent;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measId","cellsTriggeredList","numberOfReportsSent" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measId","cellsTriggeredList","numberOfReportsSent" };
    }

    @Override
    public String getAsnName() {
        return "VarMeasReport";
    }

    @Override
    public String getXmlTagName() {
        return "VarMeasReport";
    }

}

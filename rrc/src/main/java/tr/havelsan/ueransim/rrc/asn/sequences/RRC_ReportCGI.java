/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_ReportCGI extends RRC_Sequence {

    public RRC_PhysCellId cellForWhichToReportCGI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellForWhichToReportCGI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellForWhichToReportCGI" };
    }

    @Override
    public String getAsnName() {
        return "ReportCGI";
    }

    @Override
    public String getXmlTagName() {
        return "ReportCGI";
    }

}

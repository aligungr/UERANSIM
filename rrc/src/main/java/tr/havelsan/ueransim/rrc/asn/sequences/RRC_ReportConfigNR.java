/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ReportConfigNR__reportType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ReportConfigNR extends RRC_Sequence {

    public RRC_ReportConfigNR__reportType reportType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportType" };
    }

    @Override
    public String getAsnName() {
        return "ReportConfigNR";
    }

    @Override
    public String getXmlTagName() {
        return "ReportConfigNR";
    }

}

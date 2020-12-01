/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ReportConfigInterRAT__reportType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ReportConfigInterRAT extends RRC_Sequence {

    public RRC_ReportConfigInterRAT__reportType reportType;

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
        return "ReportConfigInterRAT";
    }

    @Override
    public String getXmlTagName() {
        return "ReportConfigInterRAT";
    }

}

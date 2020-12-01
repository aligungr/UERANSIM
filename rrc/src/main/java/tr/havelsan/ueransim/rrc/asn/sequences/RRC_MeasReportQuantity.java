/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasReportQuantity extends RRC_Sequence {

    public RRC_Boolean rsrp;
    public RRC_Boolean rsrq;
    public RRC_Boolean sinr;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rsrp","rsrq","sinr" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rsrp","rsrq","sinr" };
    }

    @Override
    public String getAsnName() {
        return "MeasReportQuantity";
    }

    @Override
    public String getXmlTagName() {
        return "MeasReportQuantity";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ReportSFTD_EUTRA extends RRC_Sequence {

    public RRC_Boolean reportSFTD_Meas;
    public RRC_Boolean reportRSRP;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportSFTD-Meas","reportRSRP" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportSFTD_Meas","reportRSRP" };
    }

    @Override
    public String getAsnName() {
        return "ReportSFTD-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "ReportSFTD-EUTRA";
    }

}

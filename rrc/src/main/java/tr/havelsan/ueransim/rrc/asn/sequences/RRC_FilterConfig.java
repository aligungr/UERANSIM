/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FilterCoefficient;

public class RRC_FilterConfig extends RRC_Sequence {

    public RRC_FilterCoefficient filterCoefficientRSRP;
    public RRC_FilterCoefficient filterCoefficientRSRQ;
    public RRC_FilterCoefficient filterCoefficientRS_SINR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "filterCoefficientRSRP","filterCoefficientRSRQ","filterCoefficientRS-SINR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "filterCoefficientRSRP","filterCoefficientRSRQ","filterCoefficientRS_SINR" };
    }

    @Override
    public String getAsnName() {
        return "FilterConfig";
    }

    @Override
    public String getXmlTagName() {
        return "FilterConfig";
    }

}

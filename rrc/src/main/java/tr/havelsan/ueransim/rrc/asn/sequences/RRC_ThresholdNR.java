/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRQ_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SINR_Range;

public class RRC_ThresholdNR extends RRC_Sequence {

    public RRC_RSRP_Range thresholdRSRP;
    public RRC_RSRQ_Range thresholdRSRQ;
    public RRC_SINR_Range thresholdSINR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "thresholdRSRP","thresholdRSRQ","thresholdSINR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "thresholdRSRP","thresholdRSRQ","thresholdSINR" };
    }

    @Override
    public String getAsnName() {
        return "ThresholdNR";
    }

    @Override
    public String getXmlTagName() {
        return "ThresholdNR";
    }

}

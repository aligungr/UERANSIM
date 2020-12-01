/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRQ_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SINR_RangeEUTRA;

public class RRC_MeasQuantityResultsEUTRA extends RRC_Sequence {

    public RRC_RSRP_RangeEUTRA rsrp;
    public RRC_RSRQ_RangeEUTRA rsrq;
    public RRC_SINR_RangeEUTRA sinr;

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
        return "MeasQuantityResultsEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasQuantityResultsEUTRA";
    }

}

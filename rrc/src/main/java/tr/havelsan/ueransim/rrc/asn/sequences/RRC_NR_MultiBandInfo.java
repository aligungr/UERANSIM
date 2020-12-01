/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_NR_NS_PmaxList;

public class RRC_NR_MultiBandInfo extends RRC_Sequence {

    public RRC_FreqBandIndicatorNR freqBandIndicatorNR;
    public RRC_NR_NS_PmaxList nr_NS_PmaxList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "freqBandIndicatorNR","nr-NS-PmaxList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "freqBandIndicatorNR","nr_NS_PmaxList" };
    }

    @Override
    public String getAsnName() {
        return "NR-MultiBandInfo";
    }

    @Override
    public String getXmlTagName() {
        return "NR-MultiBandInfo";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_NS_PmaxList;

public class RRC_EUTRA_MultiBandInfo extends RRC_Sequence {

    public RRC_FreqBandIndicatorEUTRA eutra_FreqBandIndicator;
    public RRC_EUTRA_NS_PmaxList eutra_NS_PmaxList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra-FreqBandIndicator","eutra-NS-PmaxList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra_FreqBandIndicator","eutra_NS_PmaxList" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-MultiBandInfo";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-MultiBandInfo";
    }

}

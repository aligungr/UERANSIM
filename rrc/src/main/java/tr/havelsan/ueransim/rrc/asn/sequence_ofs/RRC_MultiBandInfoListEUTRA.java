/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;

public class RRC_MultiBandInfoListEUTRA extends RRC_SequenceOf<RRC_FreqBandIndicatorEUTRA> {

    @Override
    public String getAsnName() {
        return "MultiBandInfoListEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MultiBandInfoListEUTRA";
    }

    @Override
    public Class<RRC_FreqBandIndicatorEUTRA> getItemType() {
        return RRC_FreqBandIndicatorEUTRA.class;
    }

}

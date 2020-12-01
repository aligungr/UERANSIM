/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_NR_MultiBandInfo;

public class RRC_MultiFrequencyBandListNR_SIB extends RRC_SequenceOf<RRC_NR_MultiBandInfo> {

    @Override
    public String getAsnName() {
        return "MultiFrequencyBandListNR-SIB";
    }

    @Override
    public String getXmlTagName() {
        return "MultiFrequencyBandListNR-SIB";
    }

    @Override
    public Class<RRC_NR_MultiBandInfo> getItemType() {
        return RRC_NR_MultiBandInfo.class;
    }

}

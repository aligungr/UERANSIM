/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandCombinationInfo;

public class RRC_BandCombinationInfoList extends RRC_SequenceOf<RRC_BandCombinationInfo> {

    @Override
    public String getAsnName() {
        return "BandCombinationInfoList";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombinationInfoList";
    }

    @Override
    public Class<RRC_BandCombinationInfo> getItemType() {
        return RRC_BandCombinationInfo.class;
    }

}

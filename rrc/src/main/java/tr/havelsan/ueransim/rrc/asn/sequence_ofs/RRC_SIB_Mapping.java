/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SIB_TypeInfo;

public class RRC_SIB_Mapping extends RRC_SequenceOf<RRC_SIB_TypeInfo> {

    @Override
    public String getAsnName() {
        return "SIB-Mapping";
    }

    @Override
    public String getXmlTagName() {
        return "SIB-Mapping";
    }

    @Override
    public Class<RRC_SIB_TypeInfo> getItemType() {
        return RRC_SIB_TypeInfo.class;
    }

}

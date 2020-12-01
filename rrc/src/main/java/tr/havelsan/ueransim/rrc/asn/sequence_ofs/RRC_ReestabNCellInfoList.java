/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReestabNCellInfo;

public class RRC_ReestabNCellInfoList extends RRC_SequenceOf<RRC_ReestabNCellInfo> {

    @Override
    public String getAsnName() {
        return "ReestabNCellInfoList";
    }

    @Override
    public String getXmlTagName() {
        return "ReestabNCellInfoList";
    }

    @Override
    public Class<RRC_ReestabNCellInfo> getItemType() {
        return RRC_ReestabNCellInfo.class;
    }

}

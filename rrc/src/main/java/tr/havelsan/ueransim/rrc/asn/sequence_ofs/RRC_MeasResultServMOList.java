/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResultServMO;

public class RRC_MeasResultServMOList extends RRC_SequenceOf<RRC_MeasResultServMO> {

    @Override
    public String getAsnName() {
        return "MeasResultServMOList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultServMOList";
    }

    @Override
    public Class<RRC_MeasResultServMO> getItemType() {
        return RRC_MeasResultServMO.class;
    }

}

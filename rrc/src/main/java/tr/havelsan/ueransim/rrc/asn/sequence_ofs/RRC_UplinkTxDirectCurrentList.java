/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UplinkTxDirectCurrentCell;

public class RRC_UplinkTxDirectCurrentList extends RRC_SequenceOf<RRC_UplinkTxDirectCurrentCell> {

    @Override
    public String getAsnName() {
        return "UplinkTxDirectCurrentList";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkTxDirectCurrentList";
    }

    @Override
    public Class<RRC_UplinkTxDirectCurrentCell> getItemType() {
        return RRC_UplinkTxDirectCurrentCell.class;
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkTxDirectCurrentCell__uplinkDirectCurrentBWP;

public class RRC_UplinkTxDirectCurrentCell extends RRC_Sequence {

    public RRC_ServCellIndex servCellIndex;
    public RRC_UplinkTxDirectCurrentCell__uplinkDirectCurrentBWP uplinkDirectCurrentBWP;
    public RRC_UplinkTxDirectCurrentCell__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servCellIndex","uplinkDirectCurrentBWP","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servCellIndex","uplinkDirectCurrentBWP","ext1" };
    }

    @Override
    public String getAsnName() {
        return "UplinkTxDirectCurrentCell";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkTxDirectCurrentCell";
    }

}

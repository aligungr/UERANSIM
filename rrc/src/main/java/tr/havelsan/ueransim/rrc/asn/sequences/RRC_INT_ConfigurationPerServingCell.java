/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_INT_ConfigurationPerServingCell extends RRC_Sequence {

    public RRC_ServCellIndex servingCellId;
    public RRC_Integer positionInDCI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servingCellId","positionInDCI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servingCellId","positionInDCI" };
    }

    @Override
    public String getAsnName() {
        return "INT-ConfigurationPerServingCell";
    }

    @Override
    public String getXmlTagName() {
        return "INT-ConfigurationPerServingCell";
    }

}

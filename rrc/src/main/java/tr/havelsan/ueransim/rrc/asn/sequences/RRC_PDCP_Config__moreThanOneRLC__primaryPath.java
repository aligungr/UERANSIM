/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;

public class RRC_PDCP_Config__moreThanOneRLC__primaryPath extends RRC_Sequence {

    public RRC_CellGroupId cellGroup;
    public RRC_LogicalChannelIdentity logicalChannel;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellGroup","logicalChannel" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellGroup","logicalChannel" };
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;

public class RRC_FailureInfoRLC_Bearer extends RRC_Sequence {

    public RRC_CellGroupId cellGroupId;
    public RRC_LogicalChannelIdentity logicalChannelIdentity;
    public RRC_Integer failureType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellGroupId","logicalChannelIdentity","failureType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellGroupId","logicalChannelIdentity","failureType" };
    }

    @Override
    public String getAsnName() {
        return "FailureInfoRLC-Bearer";
    }

    @Override
    public String getXmlTagName() {
        return "FailureInfoRLC-Bearer";
    }

}

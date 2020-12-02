/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_UL_DataSplitThreshold;

public class RRC_PDCP_Config__moreThanOneRLC extends RRC_Sequence {

    public RRC_PDCP_Config__moreThanOneRLC__primaryPath primaryPath;
    public RRC_UL_DataSplitThreshold ul_DataSplitThreshold;
    public RRC_Boolean pdcp_Duplication;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "primaryPath","ul-DataSplitThreshold","pdcp-Duplication" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "primaryPath","ul_DataSplitThreshold","pdcp_Duplication" };
    }

}

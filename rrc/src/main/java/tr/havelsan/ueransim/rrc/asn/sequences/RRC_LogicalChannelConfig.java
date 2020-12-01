/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_LogicalChannelConfig extends RRC_Sequence {

    public RRC_LogicalChannelConfig__ul_SpecificParameters ul_SpecificParameters;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ul-SpecificParameters" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ul_SpecificParameters" };
    }

    @Override
    public String getAsnName() {
        return "LogicalChannelConfig";
    }

    @Override
    public String getXmlTagName() {
        return "LogicalChannelConfig";
    }

}

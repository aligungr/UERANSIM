/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RLC_BearerConfig__servedRadioBearer;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RLC_Config;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_LogicalChannelIdentity;

public class RRC_RLC_BearerConfig extends RRC_Sequence {

    public RRC_LogicalChannelIdentity logicalChannelIdentity;
    public RRC_RLC_BearerConfig__servedRadioBearer servedRadioBearer;
    public RRC_Integer reestablishRLC;
    public RRC_RLC_Config rlc_Config;
    public RRC_LogicalChannelConfig mac_LogicalChannelConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "logicalChannelIdentity","servedRadioBearer","reestablishRLC","rlc-Config","mac-LogicalChannelConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "logicalChannelIdentity","servedRadioBearer","reestablishRLC","rlc_Config","mac_LogicalChannelConfig" };
    }

    @Override
    public String getAsnName() {
        return "RLC-BearerConfig";
    }

    @Override
    public String getXmlTagName() {
        return "RLC-BearerConfig";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BSR_Config extends RRC_Sequence {

    public RRC_Integer periodicBSR_Timer;
    public RRC_Integer retxBSR_Timer;
    public RRC_Integer logicalChannelSR_DelayTimer;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodicBSR-Timer","retxBSR-Timer","logicalChannelSR-DelayTimer" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodicBSR_Timer","retxBSR_Timer","logicalChannelSR_DelayTimer" };
    }

    @Override
    public String getAsnName() {
        return "BSR-Config";
    }

    @Override
    public String getXmlTagName() {
        return "BSR-Config";
    }

}

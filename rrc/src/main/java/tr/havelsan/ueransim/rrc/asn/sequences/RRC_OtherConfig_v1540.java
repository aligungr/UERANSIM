/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_OverheatingAssistanceConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_OtherConfig_v1540 extends RRC_Sequence {

    public RRC_SetupRelease_OverheatingAssistanceConfig overheatingAssistanceConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "overheatingAssistanceConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "overheatingAssistanceConfig" };
    }

    @Override
    public String getAsnName() {
        return "OtherConfig-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "OtherConfig-v1540";
    }

}

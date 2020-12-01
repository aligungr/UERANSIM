/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DRX_Config;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PHR_Config;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MAC_CellGroupConfig extends RRC_Sequence {

    public RRC_SetupRelease_DRX_Config drx_Config;
    public RRC_SchedulingRequestConfig schedulingRequestConfig;
    public RRC_BSR_Config bsr_Config;
    public RRC_TAG_Config tag_Config;
    public RRC_SetupRelease_PHR_Config phr_Config;
    public RRC_Boolean skipUplinkTxDynamic;
    public RRC_MAC_CellGroupConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drx-Config","schedulingRequestConfig","bsr-Config","tag-Config","phr-Config","skipUplinkTxDynamic","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drx_Config","schedulingRequestConfig","bsr_Config","tag_Config","phr_Config","skipUplinkTxDynamic","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MAC-CellGroupConfig";
    }

    @Override
    public String getXmlTagName() {
        return "MAC-CellGroupConfig";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RLC_Config__am;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RLC_Config__um_Bi_Directional;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RLC_Config__um_Uni_Directional_DL;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RLC_Config__um_Uni_Directional_UL;

public class RRC_RLC_Config extends RRC_Choice {

    public RRC_RLC_Config__am am;
    public RRC_RLC_Config__um_Bi_Directional um_Bi_Directional;
    public RRC_RLC_Config__um_Uni_Directional_UL um_Uni_Directional_UL;
    public RRC_RLC_Config__um_Uni_Directional_DL um_Uni_Directional_DL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "am","um-Bi-Directional","um-Uni-Directional-UL","um-Uni-Directional-DL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "am","um_Bi_Directional","um_Uni_Directional_UL","um_Uni_Directional_DL" };
    }

    @Override
    public String getAsnName() {
        return "RLC-Config";
    }

    @Override
    public String getXmlTagName() {
        return "RLC-Config";
    }

}

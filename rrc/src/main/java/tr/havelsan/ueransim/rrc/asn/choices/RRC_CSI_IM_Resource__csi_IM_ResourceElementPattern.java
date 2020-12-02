/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern__pattern0;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern__pattern1;

public class RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern extends RRC_Choice {

    public RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern__pattern0 pattern0;
    public RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern__pattern1 pattern1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pattern0","pattern1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pattern0","pattern1" };
    }

}

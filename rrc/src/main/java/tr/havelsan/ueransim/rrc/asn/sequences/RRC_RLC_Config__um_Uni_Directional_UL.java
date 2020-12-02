/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RLC_Config__um_Uni_Directional_UL extends RRC_Sequence {

    public RRC_UL_UM_RLC ul_UM_RLC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ul-UM-RLC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ul_UM_RLC" };
    }

}

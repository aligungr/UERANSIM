/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RLC_Config__um_Uni_Directional_DL extends RRC_Sequence {

    public RRC_DL_UM_RLC dl_UM_RLC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dl-UM-RLC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dl_UM_RLC" };
    }

}

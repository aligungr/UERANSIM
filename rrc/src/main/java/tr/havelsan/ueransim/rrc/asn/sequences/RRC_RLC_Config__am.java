/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RLC_Config__am extends RRC_Sequence {

    public RRC_UL_AM_RLC ul_AM_RLC;
    public RRC_DL_AM_RLC dl_AM_RLC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ul-AM-RLC","dl-AM-RLC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ul_AM_RLC","dl_AM_RLC" };
    }

}

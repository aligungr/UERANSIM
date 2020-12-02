/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandNR__channelBWs_DL_v1530__fr2 extends RRC_Sequence {

    public RRC_BitString scs_60kHz;
    public RRC_BitString scs_120kHz;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scs-60kHz","scs-120kHz" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scs_60kHz","scs_120kHz" };
    }

}

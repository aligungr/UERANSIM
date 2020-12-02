/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetUplink_v1540__pusch_ProcessingType2 extends RRC_Sequence {

    public RRC_ProcessingParameters scs_15kHz;
    public RRC_ProcessingParameters scs_30kHz;
    public RRC_ProcessingParameters scs_60kHz;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scs-15kHz","scs-30kHz","scs-60kHz" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scs_15kHz","scs_30kHz","scs_60kHz" };
    }

}

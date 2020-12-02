/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MIMO_ParametersPerBand__maxNumberRxTxBeamSwitchDL extends RRC_Sequence {

    public RRC_Integer scs_15kHz;
    public RRC_Integer scs_30kHz;
    public RRC_Integer scs_60kHz;
    public RRC_Integer scs_120kHz;
    public RRC_Integer scs_240kHz;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "scs-15kHz","scs-30kHz","scs-60kHz","scs-120kHz","scs-240kHz" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "scs_15kHz","scs_30kHz","scs_60kHz","scs_120kHz","scs_240kHz" };
    }

}

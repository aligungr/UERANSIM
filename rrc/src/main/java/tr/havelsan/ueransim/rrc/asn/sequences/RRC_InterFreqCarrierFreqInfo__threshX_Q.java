/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThresholdQ;

public class RRC_InterFreqCarrierFreqInfo__threshX_Q extends RRC_Sequence {

    public RRC_ReselectionThresholdQ threshX_HighQ;
    public RRC_ReselectionThresholdQ threshX_LowQ;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "threshX-HighQ","threshX-LowQ" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "threshX_HighQ","threshX_LowQ" };
    }

}

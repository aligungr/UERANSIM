/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_Resource__transmissionComb__n4 extends RRC_Sequence {

    public RRC_Integer combOffset_n4;
    public RRC_Integer cyclicShift_n4;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "combOffset-n4","cyclicShift-n4" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "combOffset_n4","cyclicShift_n4" };
    }

}

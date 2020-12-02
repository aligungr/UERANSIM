/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_ConfigRestrictInfoSCG__servCellIndexRangeSCG extends RRC_Sequence {

    public RRC_ServCellIndex lowBound;
    public RRC_ServCellIndex upBound;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "lowBound","upBound" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "lowBound","upBound" };
    }

}

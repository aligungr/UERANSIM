/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PRACH_ResourceDedicatedBFR;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_BeamFailureRecoveryConfig__candidateBeamRSList extends RRC_SequenceOf<RRC_PRACH_ResourceDedicatedBFR> {

    @Override
    public Class<RRC_PRACH_ResourceDedicatedBFR> getItemType() {
        return RRC_PRACH_ResourceDedicatedBFR.class;
    }

}

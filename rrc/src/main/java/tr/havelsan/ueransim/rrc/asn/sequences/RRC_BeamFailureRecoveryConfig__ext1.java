/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_BeamFailureRecoveryConfig__ext1 extends RRC_Sequence {

    public RRC_SubcarrierSpacing msg1_SubcarrierSpacing_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "msg1-SubcarrierSpacing-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "msg1_SubcarrierSpacing_v1530" };
    }

}

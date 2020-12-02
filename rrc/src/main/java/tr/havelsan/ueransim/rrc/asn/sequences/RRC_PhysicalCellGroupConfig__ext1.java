/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_PhysicalCellGroupConfig__ext1 extends RRC_Sequence {

    public RRC_RNTI_Value mcs_C_RNTI;
    public RRC_P_Max p_UE_FR1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mcs-C-RNTI","p-UE-FR1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mcs_C_RNTI","p_UE_FR1" };
    }

}

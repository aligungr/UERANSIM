/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SN_FieldLengthUM;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_T_Reassembly;

public class RRC_DL_UM_RLC extends RRC_Sequence {

    public RRC_SN_FieldLengthUM sn_FieldLength;
    public RRC_T_Reassembly t_Reassembly;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sn-FieldLength","t-Reassembly" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sn_FieldLength","t_Reassembly" };
    }

    @Override
    public String getAsnName() {
        return "DL-UM-RLC";
    }

    @Override
    public String getXmlTagName() {
        return "DL-UM-RLC";
    }

}

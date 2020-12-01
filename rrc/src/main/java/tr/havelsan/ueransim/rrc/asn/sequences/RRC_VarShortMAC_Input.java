/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_VarShortMAC_Input extends RRC_Sequence {

    public RRC_PhysCellId sourcePhysCellId;
    public RRC_CellIdentity targetCellIdentity;
    public RRC_RNTI_Value source_c_RNTI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sourcePhysCellId","targetCellIdentity","source-c-RNTI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sourcePhysCellId","targetCellIdentity","source_c_RNTI" };
    }

    @Override
    public String getAsnName() {
        return "VarShortMAC-Input";
    }

    @Override
    public String getXmlTagName() {
        return "VarShortMAC-Input";
    }

}

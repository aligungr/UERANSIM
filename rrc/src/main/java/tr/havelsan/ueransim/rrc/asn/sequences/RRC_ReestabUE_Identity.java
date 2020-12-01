/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortMAC_I;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_ReestabUE_Identity extends RRC_Sequence {

    public RRC_RNTI_Value c_RNTI;
    public RRC_PhysCellId physCellId;
    public RRC_ShortMAC_I shortMAC_I;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "c-RNTI","physCellId","shortMAC-I" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "c_RNTI","physCellId","shortMAC_I" };
    }

    @Override
    public String getAsnName() {
        return "ReestabUE-Identity";
    }

    @Override
    public String getXmlTagName() {
        return "ReestabUE-Identity";
    }

}

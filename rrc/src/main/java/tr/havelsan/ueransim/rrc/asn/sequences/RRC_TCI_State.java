/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_TCI_State extends RRC_Sequence {

    public RRC_TCI_StateId tci_StateId;
    public RRC_QCL_Info qcl_Type1;
    public RRC_QCL_Info qcl_Type2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tci-StateId","qcl-Type1","qcl-Type2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tci_StateId","qcl_Type1","qcl_Type2" };
    }

    @Override
    public String getAsnName() {
        return "TCI-State";
    }

    @Override
    public String getXmlTagName() {
        return "TCI-State";
    }

}

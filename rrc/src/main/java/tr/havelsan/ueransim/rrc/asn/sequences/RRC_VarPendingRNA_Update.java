/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_VarPendingRNA_Update extends RRC_Sequence {

    public RRC_Boolean pendingRNA_Update;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pendingRNA-Update" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pendingRNA_Update" };
    }

    @Override
    public String getAsnName() {
        return "VarPendingRNA-Update";
    }

    @Override
    public String getXmlTagName() {
        return "VarPendingRNA-Update";
    }

}

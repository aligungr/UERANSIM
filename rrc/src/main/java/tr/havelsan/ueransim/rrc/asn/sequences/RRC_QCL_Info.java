/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_QCL_Info__referenceSignal;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_QCL_Info extends RRC_Sequence {

    public RRC_ServCellIndex cell;
    public RRC_BWP_Id bwp_Id;
    public RRC_QCL_Info__referenceSignal referenceSignal;
    public RRC_Integer qcl_Type;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cell","bwp-Id","referenceSignal","qcl-Type" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cell","bwp_Id","referenceSignal","qcl_Type" };
    }

    @Override
    public String getAsnName() {
        return "QCL-Info";
    }

    @Override
    public String getXmlTagName() {
        return "QCL-Info";
    }

}

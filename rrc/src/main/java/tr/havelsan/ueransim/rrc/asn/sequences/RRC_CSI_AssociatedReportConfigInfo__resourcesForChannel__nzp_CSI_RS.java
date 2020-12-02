/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS__qcl_info;

public class RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS extends RRC_Sequence {

    public RRC_Integer resourceSet;
    public RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS__qcl_info qcl_info;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resourceSet","qcl-info" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resourceSet","qcl_info" };
    }

}

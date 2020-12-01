/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;

public class RRC_CSI_AssociatedReportConfigInfo extends RRC_Sequence {

    public RRC_CSI_ReportConfigId reportConfigId;
    public RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel resourcesForChannel;
    public RRC_Integer csi_IM_ResourcesForInterference;
    public RRC_Integer nzp_CSI_RS_ResourcesForInterference;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportConfigId","resourcesForChannel","csi-IM-ResourcesForInterference","nzp-CSI-RS-ResourcesForInterference" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportConfigId","resourcesForChannel","csi_IM_ResourcesForInterference","nzp_CSI_RS_ResourcesForInterference" };
    }

    @Override
    public String getAsnName() {
        return "CSI-AssociatedReportConfigInfo";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-AssociatedReportConfigInfo";
    }

}

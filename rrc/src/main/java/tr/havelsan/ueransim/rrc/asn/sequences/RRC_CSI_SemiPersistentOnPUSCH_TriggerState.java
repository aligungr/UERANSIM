/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;

public class RRC_CSI_SemiPersistentOnPUSCH_TriggerState extends RRC_Sequence {

    public RRC_CSI_ReportConfigId associatedReportConfigInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "associatedReportConfigInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "associatedReportConfigInfo" };
    }

    @Override
    public String getAsnName() {
        return "CSI-SemiPersistentOnPUSCH-TriggerState";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-SemiPersistentOnPUSCH-TriggerState";
    }

}

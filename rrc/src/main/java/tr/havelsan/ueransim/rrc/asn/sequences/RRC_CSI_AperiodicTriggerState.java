/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_AperiodicTriggerState__associatedReportConfigInfoList;

public class RRC_CSI_AperiodicTriggerState extends RRC_Sequence {

    public RRC_CSI_AperiodicTriggerState__associatedReportConfigInfoList associatedReportConfigInfoList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "associatedReportConfigInfoList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "associatedReportConfigInfoList" };
    }

    @Override
    public String getAsnName() {
        return "CSI-AperiodicTriggerState";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-AperiodicTriggerState";
    }

}

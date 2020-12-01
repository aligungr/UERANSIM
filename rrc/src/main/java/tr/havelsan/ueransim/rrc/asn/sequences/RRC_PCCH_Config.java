/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_PCCH_Config__nAndPagingFrameOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PagingCycle;

public class RRC_PCCH_Config extends RRC_Sequence {

    public RRC_PagingCycle defaultPagingCycle;
    public RRC_PCCH_Config__nAndPagingFrameOffset nAndPagingFrameOffset;
    public RRC_Integer ns;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO firstPDCCH_MonitoringOccasionOfPO;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "defaultPagingCycle","nAndPagingFrameOffset","ns","firstPDCCH-MonitoringOccasionOfPO" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "defaultPagingCycle","nAndPagingFrameOffset","ns","firstPDCCH_MonitoringOccasionOfPO" };
    }

    @Override
    public String getAsnName() {
        return "PCCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PCCH-Config";
    }

}

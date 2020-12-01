/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RadioLinkMonitoringRS__detectionResource;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RadioLinkMonitoringRS_Id;

public class RRC_RadioLinkMonitoringRS extends RRC_Sequence {

    public RRC_RadioLinkMonitoringRS_Id radioLinkMonitoringRS_Id;
    public RRC_Integer purpose;
    public RRC_RadioLinkMonitoringRS__detectionResource detectionResource;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "radioLinkMonitoringRS-Id","purpose","detectionResource" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "radioLinkMonitoringRS_Id","purpose","detectionResource" };
    }

    @Override
    public String getAsnName() {
        return "RadioLinkMonitoringRS";
    }

    @Override
    public String getXmlTagName() {
        return "RadioLinkMonitoringRS";
    }

}

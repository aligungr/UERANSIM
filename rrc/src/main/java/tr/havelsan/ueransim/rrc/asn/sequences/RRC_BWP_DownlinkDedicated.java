/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDCCH_Config;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDSCH_Config;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RadioLinkMonitoringConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_SPS_Config;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BWP_DownlinkDedicated extends RRC_Sequence {

    public RRC_SetupRelease_PDCCH_Config pdcch_Config;
    public RRC_SetupRelease_PDSCH_Config pdsch_Config;
    public RRC_SetupRelease_SPS_Config sps_Config;
    public RRC_SetupRelease_RadioLinkMonitoringConfig radioLinkMonitoringConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdcch-Config","pdsch-Config","sps-Config","radioLinkMonitoringConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdcch_Config","pdsch_Config","sps_Config","radioLinkMonitoringConfig" };
    }

    @Override
    public String getAsnName() {
        return "BWP-DownlinkDedicated";
    }

    @Override
    public String getXmlTagName() {
        return "BWP-DownlinkDedicated";
    }

}

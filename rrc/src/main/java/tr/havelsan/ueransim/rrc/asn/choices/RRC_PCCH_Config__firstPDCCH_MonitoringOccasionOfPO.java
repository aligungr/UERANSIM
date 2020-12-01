/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO extends RRC_Choice {

    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS15KHZoneT sCS15KHZoneT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS30KHZoneT_SCS15KHZhalfT sCS30KHZoneT_SCS15KHZhalfT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZoneEighthT_SCS60KHZoneSixteenthT sCS120KHZoneEighthT_SCS60KHZoneSixteenthT;
    public RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZoneSixteenthT sCS120KHZoneSixteenthT;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sCS15KHZoneT","sCS30KHZoneT-SCS15KHZhalfT","sCS60KHZoneT-SCS30KHZhalfT-SCS15KHZquarterT","sCS120KHZoneT-SCS60KHZhalfT-SCS30KHZquarterT-SCS15KHZoneEighthT","sCS120KHZhalfT-SCS60KHZquarterT-SCS30KHZoneEighthT-SCS15KHZoneSixteenthT","sCS120KHZquarterT-SCS60KHZoneEighthT-SCS30KHZoneSixteenthT","sCS120KHZoneEighthT-SCS60KHZoneSixteenthT","sCS120KHZoneSixteenthT" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sCS15KHZoneT","sCS30KHZoneT_SCS15KHZhalfT","sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT","sCS120KHZoneT_SCS60KHZhalfT_SCS30KHZquarterT_SCS15KHZoneEighthT","sCS120KHZhalfT_SCS60KHZquarterT_SCS30KHZoneEighthT_SCS15KHZoneSixteenthT","sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT","sCS120KHZoneEighthT_SCS60KHZoneSixteenthT","sCS120KHZoneSixteenthT" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SSB_ConfigMobility extends RRC_Sequence {

    public RRC_SetupRelease_SSB_ToMeasure ssb_ToMeasure;
    public RRC_Boolean deriveSSB_IndexFromCell;
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-ToMeasure","deriveSSB-IndexFromCell","ss-RSSI-Measurement" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_ToMeasure","deriveSSB_IndexFromCell","ss_RSSI_Measurement" };
    }

    @Override
    public String getAsnName() {
        return "SSB-ConfigMobility";
    }

    @Override
    public String getXmlTagName() {
        return "SSB-ConfigMobility";
    }

}

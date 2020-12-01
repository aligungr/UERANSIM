/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_CSI_RS_ResourceConfigMobility;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ReferenceSignalConfig extends RRC_Sequence {

    public RRC_SSB_ConfigMobility ssb_ConfigMobility;
    public RRC_SetupRelease_CSI_RS_ResourceConfigMobility csi_rs_ResourceConfigMobility;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-ConfigMobility","csi-rs-ResourceConfigMobility" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_ConfigMobility","csi_rs_ResourceConfigMobility" };
    }

    @Override
    public String getAsnName() {
        return "ReferenceSignalConfig";
    }

    @Override
    public String getXmlTagName() {
        return "ReferenceSignalConfig";
    }

}

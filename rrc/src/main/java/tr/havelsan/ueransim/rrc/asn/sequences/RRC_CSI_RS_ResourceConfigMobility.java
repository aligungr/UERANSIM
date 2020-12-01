/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_RS_ResourceConfigMobility__csi_RS_CellList_Mobility;

public class RRC_CSI_RS_ResourceConfigMobility extends RRC_Sequence {

    public RRC_SubcarrierSpacing subcarrierSpacing;
    public RRC_CSI_RS_ResourceConfigMobility__csi_RS_CellList_Mobility csi_RS_CellList_Mobility;
    public RRC_CSI_RS_ResourceConfigMobility__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "subcarrierSpacing","csi-RS-CellList-Mobility","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "subcarrierSpacing","csi_RS_CellList_Mobility","ext1" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-ResourceConfigMobility";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-ResourceConfigMobility";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourceConfig__csi_RS_ResourceSetList;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ResourceConfigId;

public class RRC_CSI_ResourceConfig extends RRC_Sequence {

    public RRC_CSI_ResourceConfigId csi_ResourceConfigId;
    public RRC_CSI_ResourceConfig__csi_RS_ResourceSetList csi_RS_ResourceSetList;
    public RRC_BWP_Id bwp_Id;
    public RRC_Integer resourceType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-ResourceConfigId","csi-RS-ResourceSetList","bwp-Id","resourceType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_ResourceConfigId","csi_RS_ResourceSetList","bwp_Id","resourceType" };
    }

    @Override
    public String getAsnName() {
        return "CSI-ResourceConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-ResourceConfig";
    }

}

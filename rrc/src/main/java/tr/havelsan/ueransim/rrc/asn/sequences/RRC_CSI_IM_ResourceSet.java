/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_IM_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_IM_ResourceSet__csi_IM_Resources;

public class RRC_CSI_IM_ResourceSet extends RRC_Sequence {

    public RRC_CSI_IM_ResourceSetId csi_IM_ResourceSetId;
    public RRC_CSI_IM_ResourceSet__csi_IM_Resources csi_IM_Resources;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-IM-ResourceSetId","csi-IM-Resources" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_IM_ResourceSetId","csi_IM_Resources" };
    }

    @Override
    public String getAsnName() {
        return "CSI-IM-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-IM-ResourceSet";
    }

}

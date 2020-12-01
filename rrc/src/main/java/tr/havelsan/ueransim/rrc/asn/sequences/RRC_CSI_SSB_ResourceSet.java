/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_SSB_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_SSB_ResourceSet__csi_SSB_ResourceList;

public class RRC_CSI_SSB_ResourceSet extends RRC_Sequence {

    public RRC_CSI_SSB_ResourceSetId csi_SSB_ResourceSetId;
    public RRC_CSI_SSB_ResourceSet__csi_SSB_ResourceList csi_SSB_ResourceList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-SSB-ResourceSetId","csi-SSB-ResourceList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_SSB_ResourceSetId","csi_SSB_ResourceList" };
    }

    @Override
    public String getAsnName() {
        return "CSI-SSB-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-SSB-ResourceSet";
    }

}

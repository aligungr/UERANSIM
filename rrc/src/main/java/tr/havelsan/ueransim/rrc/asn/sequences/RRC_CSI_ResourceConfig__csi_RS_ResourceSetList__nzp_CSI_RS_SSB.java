/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB__csi_SSB_ResourceSetList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB__nzp_CSI_RS_ResourceSetList;

public class RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB extends RRC_Sequence {

    public RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB__nzp_CSI_RS_ResourceSetList nzp_CSI_RS_ResourceSetList;
    public RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB__csi_SSB_ResourceSetList csi_SSB_ResourceSetList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-RS-ResourceSetList","csi-SSB-ResourceSetList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_RS_ResourceSetList","csi_SSB_ResourceSetList" };
    }

}

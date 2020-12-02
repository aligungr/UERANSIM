/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__csi_IM_ResourceSetList;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB;

public class RRC_CSI_ResourceConfig__csi_RS_ResourceSetList extends RRC_Choice {

    public RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__nzp_CSI_RS_SSB nzp_CSI_RS_SSB;
    public RRC_CSI_ResourceConfig__csi_RS_ResourceSetList__csi_IM_ResourceSetList csi_IM_ResourceSetList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-RS-SSB","csi-IM-ResourceSetList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_RS_SSB","csi_IM_ResourceSetList" };
    }

}

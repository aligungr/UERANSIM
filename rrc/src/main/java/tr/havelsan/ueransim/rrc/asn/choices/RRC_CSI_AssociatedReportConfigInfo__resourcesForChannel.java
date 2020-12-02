/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS;

public class RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel extends RRC_Choice {

    public RRC_CSI_AssociatedReportConfigInfo__resourcesForChannel__nzp_CSI_RS nzp_CSI_RS;
    public RRC_Integer csi_SSB_ResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-RS","csi-SSB-ResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_RS","csi_SSB_ResourceSet" };
    }

}

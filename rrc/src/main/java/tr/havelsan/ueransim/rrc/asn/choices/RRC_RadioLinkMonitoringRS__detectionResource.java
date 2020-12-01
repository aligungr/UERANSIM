/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_RadioLinkMonitoringRS__detectionResource extends RRC_Choice {

    public RRC_SSB_Index ssb_Index;
    public RRC_NZP_CSI_RS_ResourceId csi_RS_Index;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-Index","csi-RS-Index" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_Index","csi_RS_Index" };
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

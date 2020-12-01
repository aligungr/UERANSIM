/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CA_ParametersNR_v1540__csi_RS_IM_ReceptionForFeedbackPerBandComb extends RRC_Sequence {

    public RRC_Integer maxNumberSimultaneousNZP_CSI_RS_ActBWP_AllCC;
    public RRC_Integer totalNumberPortsSimultaneousNZP_CSI_RS_ActBWP_AllCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSimultaneousNZP-CSI-RS-ActBWP-AllCC","totalNumberPortsSimultaneousNZP-CSI-RS-ActBWP-AllCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSimultaneousNZP_CSI_RS_ActBWP_AllCC","totalNumberPortsSimultaneousNZP_CSI_RS_ActBWP_AllCC" };
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

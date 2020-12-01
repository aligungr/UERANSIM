/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_RS_IM_ReceptionForFeedback extends RRC_Sequence {

    public RRC_Integer maxConfigNumberNZP_CSI_RS_PerCC;
    public RRC_Integer maxConfigNumberPortsAcrossNZP_CSI_RS_PerCC;
    public RRC_Integer maxConfigNumberCSI_IM_PerCC;
    public RRC_Integer maxNumberSimultaneousNZP_CSI_RS_PerCC;
    public RRC_Integer totalNumberPortsSimultaneousNZP_CSI_RS_PerCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxConfigNumberNZP-CSI-RS-PerCC","maxConfigNumberPortsAcrossNZP-CSI-RS-PerCC","maxConfigNumberCSI-IM-PerCC","maxNumberSimultaneousNZP-CSI-RS-PerCC","totalNumberPortsSimultaneousNZP-CSI-RS-PerCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxConfigNumberNZP_CSI_RS_PerCC","maxConfigNumberPortsAcrossNZP_CSI_RS_PerCC","maxConfigNumberCSI_IM_PerCC","maxNumberSimultaneousNZP_CSI_RS_PerCC","totalNumberPortsSimultaneousNZP_CSI_RS_PerCC" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-IM-ReceptionForFeedback";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-IM-ReceptionForFeedback";
    }

}

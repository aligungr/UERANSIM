/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DummyA extends RRC_Sequence {

    public RRC_Integer maxNumberNZP_CSI_RS_PerCC;
    public RRC_Integer maxNumberPortsAcrossNZP_CSI_RS_PerCC;
    public RRC_Integer maxNumberCS_IM_PerCC;
    public RRC_Integer maxNumberSimultaneousCSI_RS_ActBWP_AllCC;
    public RRC_Integer totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberNZP-CSI-RS-PerCC","maxNumberPortsAcrossNZP-CSI-RS-PerCC","maxNumberCS-IM-PerCC","maxNumberSimultaneousCSI-RS-ActBWP-AllCC","totalNumberPortsSimultaneousCSI-RS-ActBWP-AllCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberNZP_CSI_RS_PerCC","maxNumberPortsAcrossNZP_CSI_RS_PerCC","maxNumberCS_IM_PerCC","maxNumberSimultaneousCSI_RS_ActBWP_AllCC","totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC" };
    }

    @Override
    public String getAsnName() {
        return "DummyA";
    }

    @Override
    public String getXmlTagName() {
        return "DummyA";
    }

}

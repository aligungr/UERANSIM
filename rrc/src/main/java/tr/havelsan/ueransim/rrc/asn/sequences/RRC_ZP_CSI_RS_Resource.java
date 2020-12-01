/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceId;

public class RRC_ZP_CSI_RS_Resource extends RRC_Sequence {

    public RRC_ZP_CSI_RS_ResourceId zp_CSI_RS_ResourceId;
    public RRC_CSI_RS_ResourceMapping resourceMapping;
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "zp-CSI-RS-ResourceId","resourceMapping","periodicityAndOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "zp_CSI_RS_ResourceId","resourceMapping","periodicityAndOffset" };
    }

    @Override
    public String getAsnName() {
        return "ZP-CSI-RS-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "ZP-CSI-RS-Resource";
    }

}

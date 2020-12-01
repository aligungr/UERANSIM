/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_CSIRS_Resource;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_SSB_Resource;

public class RRC_PRACH_ResourceDedicatedBFR extends RRC_Choice {

    public RRC_BFR_SSB_Resource ssb;
    public RRC_BFR_CSIRS_Resource csi_RS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb","csi-RS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb","csi_RS" };
    }

    @Override
    public String getAsnName() {
        return "PRACH-ResourceDedicatedBFR";
    }

    @Override
    public String getXmlTagName() {
        return "PRACH-ResourceDedicatedBFR";
    }

}

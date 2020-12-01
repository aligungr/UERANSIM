/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRB_Identity;

public class RRC_RLC_BearerConfig__servedRadioBearer extends RRC_Choice {

    public RRC_SRB_Identity srb_Identity;
    public RRC_DRB_Identity drb_Identity;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srb-Identity","drb-Identity" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srb_Identity","drb_Identity" };
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

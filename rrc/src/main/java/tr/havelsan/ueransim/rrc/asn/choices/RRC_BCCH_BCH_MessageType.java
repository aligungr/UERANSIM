/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BCCH_BCH_MessageType__messageClassExtension;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MIB;

public class RRC_BCCH_BCH_MessageType extends RRC_Choice {

    public RRC_MIB mib;
    public RRC_BCCH_BCH_MessageType__messageClassExtension messageClassExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mib","messageClassExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mib","messageClassExtension" };
    }

    @Override
    public String getAsnName() {
        return "BCCH-BCH-MessageType";
    }

    @Override
    public String getXmlTagName() {
        return "BCCH-BCH-MessageType";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UL_DCCH_MessageType__messageClassExtension;

public class RRC_UL_DCCH_MessageType extends RRC_Choice {

    public RRC_UL_DCCH_MessageType__c1 c1;
    public RRC_UL_DCCH_MessageType__messageClassExtension messageClassExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "c1","messageClassExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "c1","messageClassExtension" };
    }

    @Override
    public String getAsnName() {
        return "UL-DCCH-MessageType";
    }

    @Override
    public String getXmlTagName() {
        return "UL-DCCH-MessageType";
    }

}

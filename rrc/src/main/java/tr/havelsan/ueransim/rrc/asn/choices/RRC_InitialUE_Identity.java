/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_InitialUE_Identity extends RRC_Choice {

    public RRC_BitString ng_5G_S_TMSI_Part1;
    public RRC_BitString randomValue;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ng-5G-S-TMSI-Part1","randomValue" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ng_5G_S_TMSI_Part1","randomValue" };
    }

    @Override
    public String getAsnName() {
        return "InitialUE-Identity";
    }

    @Override
    public String getXmlTagName() {
        return "InitialUE-Identity";
    }

}

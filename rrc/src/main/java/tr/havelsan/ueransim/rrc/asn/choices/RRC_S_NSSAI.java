/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_S_NSSAI extends RRC_Choice {

    public RRC_BitString sst;
    public RRC_BitString sst_SD;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sst","sst-SD" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sst","sst_SD" };
    }

    @Override
    public String getAsnName() {
        return "S-NSSAI";
    }

    @Override
    public String getXmlTagName() {
        return "S-NSSAI";
    }

}

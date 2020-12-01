/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_SupportedBandwidth extends RRC_Choice {

    public RRC_Integer fr1;
    public RRC_Integer fr2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "fr1","fr2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "fr1","fr2" };
    }

    @Override
    public String getAsnName() {
        return "SupportedBandwidth";
    }

    @Override
    public String getXmlTagName() {
        return "SupportedBandwidth";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_AS_Config extends RRC_Sequence {

    public RRC_OctetString rrcReconfiguration;
    public RRC_AS_Config__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcReconfiguration","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcReconfiguration","ext1" };
    }

    @Override
    public String getAsnName() {
        return "AS-Config";
    }

    @Override
    public String getXmlTagName() {
        return "AS-Config";
    }

}

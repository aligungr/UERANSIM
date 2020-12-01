/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RLF_TimersAndConstants extends RRC_Sequence {

    public RRC_Integer t310;
    public RRC_Integer n310;
    public RRC_Integer n311;
    public RRC_RLF_TimersAndConstants__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "t310","n310","n311","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "t310","n310","n311","ext1" };
    }

    @Override
    public String getAsnName() {
        return "RLF-TimersAndConstants";
    }

    @Override
    public String getXmlTagName() {
        return "RLF-TimersAndConstants";
    }

}

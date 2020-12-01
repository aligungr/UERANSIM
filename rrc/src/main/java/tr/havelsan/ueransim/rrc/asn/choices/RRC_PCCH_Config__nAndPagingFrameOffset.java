/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;

public class RRC_PCCH_Config__nAndPagingFrameOffset extends RRC_Choice {

    public RRC_Null oneT;
    public RRC_Integer halfT;
    public RRC_Integer quarterT;
    public RRC_Integer oneEighthT;
    public RRC_Integer oneSixteenthT;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "oneT","halfT","quarterT","oneEighthT","oneSixteenthT" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "oneT","halfT","quarterT","oneEighthT","oneSixteenthT" };
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

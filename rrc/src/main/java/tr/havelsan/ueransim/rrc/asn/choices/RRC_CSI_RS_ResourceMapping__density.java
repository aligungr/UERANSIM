/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;

public class RRC_CSI_RS_ResourceMapping__density extends RRC_Choice {

    public RRC_Integer dot5;
    public RRC_Null one;
    public RRC_Null three;
    public RRC_Null spare;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dot5","one","three","spare" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dot5","one","three","spare" };
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

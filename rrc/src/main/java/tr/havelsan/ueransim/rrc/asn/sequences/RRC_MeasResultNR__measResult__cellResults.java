/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasResultNR__measResult__cellResults extends RRC_Sequence {

    public RRC_MeasQuantityResults resultsSSB_Cell;
    public RRC_MeasQuantityResults resultsCSI_RS_Cell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resultsSSB-Cell","resultsCSI-RS-Cell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resultsSSB_Cell","resultsCSI_RS_Cell" };
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

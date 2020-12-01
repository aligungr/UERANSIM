/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SearchSpace__searchSpaceType__common extends RRC_Sequence {

    public RRC_SearchSpace__searchSpaceType__common__dci_Format0_0_AndFormat1_0 dci_Format0_0_AndFormat1_0;
    public RRC_SearchSpace__searchSpaceType__common__dci_Format2_0 dci_Format2_0;
    public RRC_SearchSpace__searchSpaceType__common__dci_Format2_1 dci_Format2_1;
    public RRC_SearchSpace__searchSpaceType__common__dci_Format2_2 dci_Format2_2;
    public RRC_SearchSpace__searchSpaceType__common__dci_Format2_3 dci_Format2_3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dci-Format0-0-AndFormat1-0","dci-Format2-0","dci-Format2-1","dci-Format2-2","dci-Format2-3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dci_Format0_0_AndFormat1_0","dci_Format2_0","dci_Format2_1","dci_Format2_2","dci_Format2_3" };
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

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SearchSpace__searchSpaceType__common__dci_Format2_0__nrofCandidates_SFI extends RRC_Sequence {

    public RRC_Integer aggregationLevel1;
    public RRC_Integer aggregationLevel2;
    public RRC_Integer aggregationLevel4;
    public RRC_Integer aggregationLevel8;
    public RRC_Integer aggregationLevel16;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "aggregationLevel1","aggregationLevel2","aggregationLevel4","aggregationLevel8","aggregationLevel16" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "aggregationLevel1","aggregationLevel2","aggregationLevel4","aggregationLevel8","aggregationLevel16" };
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

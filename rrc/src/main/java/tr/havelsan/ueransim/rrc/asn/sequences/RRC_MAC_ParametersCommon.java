/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MAC_ParametersCommon extends RRC_Sequence {

    public RRC_Integer lcp_Restriction;
    public RRC_Integer dummy;
    public RRC_Integer lch_ToSCellRestriction;
    public RRC_MAC_ParametersCommon__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "lcp-Restriction","dummy","lch-ToSCellRestriction","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "lcp_Restriction","dummy","lch_ToSCellRestriction","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MAC-ParametersCommon";
    }

    @Override
    public String getXmlTagName() {
        return "MAC-ParametersCommon";
    }

}

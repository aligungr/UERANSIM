/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_BFR_SSB_Resource extends RRC_Sequence {

    public RRC_SSB_Index ssb;
    public RRC_Integer ra_PreambleIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb","ra-PreambleIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb","ra_PreambleIndex" };
    }

    @Override
    public String getAsnName() {
        return "BFR-SSB-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "BFR-SSB-Resource";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUCCH_format4 extends RRC_Sequence {

    public RRC_Integer nrofSymbols;
    public RRC_Integer occ_Length;
    public RRC_Integer occ_Index;
    public RRC_Integer startingSymbolIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nrofSymbols","occ-Length","occ-Index","startingSymbolIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nrofSymbols","occ_Length","occ_Index","startingSymbolIndex" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-format4";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-format4";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUCCH_format0 extends RRC_Sequence {

    public RRC_Integer initialCyclicShift;
    public RRC_Integer nrofSymbols;
    public RRC_Integer startingSymbolIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "initialCyclicShift","nrofSymbols","startingSymbolIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "initialCyclicShift","nrofSymbols","startingSymbolIndex" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-format0";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-format0";
    }

}

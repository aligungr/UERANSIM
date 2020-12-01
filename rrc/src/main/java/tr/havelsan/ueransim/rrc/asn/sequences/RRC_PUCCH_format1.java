/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUCCH_format1 extends RRC_Sequence {

    public RRC_Integer initialCyclicShift;
    public RRC_Integer nrofSymbols;
    public RRC_Integer startingSymbolIndex;
    public RRC_Integer timeDomainOCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "initialCyclicShift","nrofSymbols","startingSymbolIndex","timeDomainOCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "initialCyclicShift","nrofSymbols","startingSymbolIndex","timeDomainOCC" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-format1";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-format1";
    }

}

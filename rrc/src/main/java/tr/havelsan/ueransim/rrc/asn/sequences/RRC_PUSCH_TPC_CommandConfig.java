/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PUSCH_TPC_CommandConfig extends RRC_Sequence {

    public RRC_Integer tpc_Index;
    public RRC_Integer tpc_IndexSUL;
    public RRC_ServCellIndex targetCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tpc-Index","tpc-IndexSUL","targetCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tpc_Index","tpc_IndexSUL","targetCell" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-TPC-CommandConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-TPC-CommandConfig";
    }

}

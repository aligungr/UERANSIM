/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUCCH_TPC_CommandConfig extends RRC_Sequence {

    public RRC_Integer tpc_IndexPCell;
    public RRC_Integer tpc_IndexPUCCH_SCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tpc-IndexPCell","tpc-IndexPUCCH-SCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tpc_IndexPCell","tpc_IndexPUCCH_SCell" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-TPC-CommandConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-TPC-CommandConfig";
    }

}

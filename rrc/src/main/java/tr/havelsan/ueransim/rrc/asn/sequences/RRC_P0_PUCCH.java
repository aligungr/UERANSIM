/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUCCH_Id;

public class RRC_P0_PUCCH extends RRC_Sequence {

    public RRC_P0_PUCCH_Id p0_PUCCH_Id;
    public RRC_Integer p0_PUCCH_Value;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "p0-PUCCH-Id","p0-PUCCH-Value" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "p0_PUCCH_Id","p0_PUCCH_Value" };
    }

    @Override
    public String getAsnName() {
        return "P0-PUCCH";
    }

    @Override
    public String getXmlTagName() {
        return "P0-PUCCH";
    }

}

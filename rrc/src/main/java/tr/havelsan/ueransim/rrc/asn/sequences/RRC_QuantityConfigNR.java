/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_QuantityConfigNR extends RRC_Sequence {

    public RRC_QuantityConfigRS quantityConfigCell;
    public RRC_QuantityConfigRS quantityConfigRS_Index;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "quantityConfigCell","quantityConfigRS-Index" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "quantityConfigCell","quantityConfigRS_Index" };
    }

    @Override
    public String getAsnName() {
        return "QuantityConfigNR";
    }

    @Override
    public String getXmlTagName() {
        return "QuantityConfigNR";
    }

}

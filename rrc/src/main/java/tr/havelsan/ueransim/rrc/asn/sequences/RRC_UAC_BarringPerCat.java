/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_UAC_BarringInfoSetIndex;

public class RRC_UAC_BarringPerCat extends RRC_Sequence {

    public RRC_Integer accessCategory;
    public RRC_UAC_BarringInfoSetIndex uac_barringInfoSetIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "accessCategory","uac-barringInfoSetIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "accessCategory","uac_barringInfoSetIndex" };
    }

    @Override
    public String getAsnName() {
        return "UAC-BarringPerCat";
    }

    @Override
    public String getXmlTagName() {
        return "UAC-BarringPerCat";
    }

}

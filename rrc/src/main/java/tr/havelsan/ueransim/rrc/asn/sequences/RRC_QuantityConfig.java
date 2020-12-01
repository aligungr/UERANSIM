/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_QuantityConfig__quantityConfigNR_List;

public class RRC_QuantityConfig extends RRC_Sequence {

    public RRC_QuantityConfig__quantityConfigNR_List quantityConfigNR_List;
    public RRC_QuantityConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "quantityConfigNR-List","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "quantityConfigNR_List","ext1" };
    }

    @Override
    public String getAsnName() {
        return "QuantityConfig";
    }

    @Override
    public String getXmlTagName() {
        return "QuantityConfig";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRQ_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SINR_Range;

public class RRC_MeasTriggerQuantity extends RRC_Choice {

    public RRC_RSRP_Range rsrp;
    public RRC_RSRQ_Range rsrq;
    public RRC_SINR_Range sinr;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rsrp","rsrq","sinr" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rsrp","rsrq","sinr" };
    }

    @Override
    public String getAsnName() {
        return "MeasTriggerQuantity";
    }

    @Override
    public String getXmlTagName() {
        return "MeasTriggerQuantity";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_MeasTriggerQuantityOffset extends RRC_Choice {

    public RRC_Integer rsrp;
    public RRC_Integer rsrq;
    public RRC_Integer sinr;

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
        return "MeasTriggerQuantityOffset";
    }

    @Override
    public String getXmlTagName() {
        return "MeasTriggerQuantityOffset";
    }

}

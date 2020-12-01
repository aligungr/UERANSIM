/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_CellReselectionSubPriority extends RRC_Enumerated {

    public static final RRC_CellReselectionSubPriority ODOT2 = new RRC_CellReselectionSubPriority("oDot2");
    public static final RRC_CellReselectionSubPriority ODOT4 = new RRC_CellReselectionSubPriority("oDot4");
    public static final RRC_CellReselectionSubPriority ODOT6 = new RRC_CellReselectionSubPriority("oDot6");
    public static final RRC_CellReselectionSubPriority ODOT8 = new RRC_CellReselectionSubPriority("oDot8");

    protected RRC_CellReselectionSubPriority(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CellReselectionSubPriority";
    }

    @Override
    public String getXmlTagName() {
        return "CellReselectionSubPriority";
    }

}

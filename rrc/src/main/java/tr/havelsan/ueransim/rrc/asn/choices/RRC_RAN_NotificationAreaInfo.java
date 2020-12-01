/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaConfigList;

public class RRC_RAN_NotificationAreaInfo extends RRC_Choice {

    public RRC_PLMN_RAN_AreaCellList cellList;
    public RRC_PLMN_RAN_AreaConfigList ran_AreaConfigList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellList","ran-AreaConfigList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellList","ran_AreaConfigList" };
    }

    @Override
    public String getAsnName() {
        return "RAN-NotificationAreaInfo";
    }

    @Override
    public String getXmlTagName() {
        return "RAN-NotificationAreaInfo";
    }

}

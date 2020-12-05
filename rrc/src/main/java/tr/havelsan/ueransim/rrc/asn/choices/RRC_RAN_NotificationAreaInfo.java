package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaConfigList;

public class RRC_RAN_NotificationAreaInfo extends AsnChoice {
    public RRC_PLMN_RAN_AreaCellList cellList;
    public RRC_PLMN_RAN_AreaConfigList ran_AreaConfigList;
}


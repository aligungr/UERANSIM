/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaConfigList;

public class RRC_RAN_NotificationAreaInfo extends AsnChoice {
    public RRC_PLMN_RAN_AreaCellList cellList;
    public RRC_PLMN_RAN_AreaConfigList ran_AreaConfigList;
}


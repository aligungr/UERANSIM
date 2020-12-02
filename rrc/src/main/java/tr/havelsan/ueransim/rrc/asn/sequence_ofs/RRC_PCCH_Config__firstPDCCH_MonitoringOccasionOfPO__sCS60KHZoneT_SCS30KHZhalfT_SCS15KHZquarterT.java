/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_PCCH_Config__firstPDCCH_MonitoringOccasionOfPO__sCS60KHZoneT_SCS30KHZhalfT_SCS15KHZquarterT extends RRC_SequenceOf<RRC_Integer> {

    @Override
    public Class<RRC_Integer> getItemType() {
        return RRC_Integer.class;
    }

}

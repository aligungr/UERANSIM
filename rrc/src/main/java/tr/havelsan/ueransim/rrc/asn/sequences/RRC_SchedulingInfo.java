/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SIB_Mapping;

public class RRC_SchedulingInfo extends RRC_Sequence {

    public RRC_Integer si_BroadcastStatus;
    public RRC_Integer si_Periodicity;
    public RRC_SIB_Mapping sib_MappingInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "si-BroadcastStatus","si-Periodicity","sib-MappingInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "si_BroadcastStatus","si_Periodicity","sib_MappingInfo" };
    }

    @Override
    public String getAsnName() {
        return "SchedulingInfo";
    }

    @Override
    public String getXmlTagName() {
        return "SchedulingInfo";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;

public class RRC_SchedulingRequestToAddMod extends RRC_Sequence {

    public RRC_SchedulingRequestId schedulingRequestId;
    public RRC_Integer sr_ProhibitTimer;
    public RRC_Integer sr_TransMax;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingRequestId","sr-ProhibitTimer","sr-TransMax" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingRequestId","sr_ProhibitTimer","sr_TransMax" };
    }

    @Override
    public String getAsnName() {
        return "SchedulingRequestToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "SchedulingRequestToAddMod";
    }

}

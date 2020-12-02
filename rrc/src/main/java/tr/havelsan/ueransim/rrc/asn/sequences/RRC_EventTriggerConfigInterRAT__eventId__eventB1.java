/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantityEUTRA;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfigInterRAT__eventId__eventB1 extends RRC_Sequence {

    public RRC_MeasTriggerQuantityEUTRA b1_ThresholdEUTRA;
    public RRC_Boolean reportOnLeave;
    public RRC_Hysteresis hysteresis;
    public RRC_TimeToTrigger timeToTrigger;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "b1-ThresholdEUTRA","reportOnLeave","hysteresis","timeToTrigger" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "b1_ThresholdEUTRA","reportOnLeave","hysteresis","timeToTrigger" };
    }

}

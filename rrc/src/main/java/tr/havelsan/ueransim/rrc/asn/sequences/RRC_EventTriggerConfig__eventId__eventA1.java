/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantity;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfig__eventId__eventA1 extends RRC_Sequence {

    public RRC_MeasTriggerQuantity a1_Threshold;
    public RRC_Boolean reportOnLeave;
    public RRC_Hysteresis hysteresis;
    public RRC_TimeToTrigger timeToTrigger;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "a1-Threshold","reportOnLeave","hysteresis","timeToTrigger" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "a1_Threshold","reportOnLeave","hysteresis","timeToTrigger" };
    }

}

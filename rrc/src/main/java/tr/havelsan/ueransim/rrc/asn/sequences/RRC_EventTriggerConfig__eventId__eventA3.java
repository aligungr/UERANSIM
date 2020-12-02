/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantityOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfig__eventId__eventA3 extends RRC_Sequence {

    public RRC_MeasTriggerQuantityOffset a3_Offset;
    public RRC_Boolean reportOnLeave;
    public RRC_Hysteresis hysteresis;
    public RRC_TimeToTrigger timeToTrigger;
    public RRC_Boolean useWhiteCellList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "a3-Offset","reportOnLeave","hysteresis","timeToTrigger","useWhiteCellList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "a3_Offset","reportOnLeave","hysteresis","timeToTrigger","useWhiteCellList" };
    }

}

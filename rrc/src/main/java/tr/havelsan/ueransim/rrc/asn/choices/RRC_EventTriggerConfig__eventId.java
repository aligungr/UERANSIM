/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_EventTriggerConfig__eventId extends RRC_Choice {

    public RRC_EventTriggerConfig__eventId__eventA1 eventA1;
    public RRC_EventTriggerConfig__eventId__eventA2 eventA2;
    public RRC_EventTriggerConfig__eventId__eventA3 eventA3;
    public RRC_EventTriggerConfig__eventId__eventA4 eventA4;
    public RRC_EventTriggerConfig__eventId__eventA5 eventA5;
    public RRC_EventTriggerConfig__eventId__eventA6 eventA6;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eventA1","eventA2","eventA3","eventA4","eventA5","eventA6" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eventA1","eventA2","eventA3","eventA4","eventA5","eventA6" };
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EventTriggerConfigInterRAT__eventId__eventB1;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EventTriggerConfigInterRAT__eventId__eventB2;

public class RRC_EventTriggerConfigInterRAT__eventId extends RRC_Choice {

    public RRC_EventTriggerConfigInterRAT__eventId__eventB1 eventB1;
    public RRC_EventTriggerConfigInterRAT__eventId__eventB2 eventB2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eventB1","eventB2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eventB1","eventB2" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantity;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasTriggerQuantityEUTRA;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeToTrigger;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Hysteresis;

public class RRC_EventTriggerConfigInterRAT__eventId__eventB2 extends RRC_Sequence {

    public RRC_MeasTriggerQuantity b2_Threshold1;
    public RRC_MeasTriggerQuantityEUTRA b2_Threshold2EUTRA;
    public RRC_Boolean reportOnLeave;
    public RRC_Hysteresis hysteresis;
    public RRC_TimeToTrigger timeToTrigger;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "b2-Threshold1","b2-Threshold2EUTRA","reportOnLeave","hysteresis","timeToTrigger" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "b2_Threshold1","b2_Threshold2EUTRA","reportOnLeave","hysteresis","timeToTrigger" };
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

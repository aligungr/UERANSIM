/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SchedulingRequestResourceConfig__periodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestResourceId;

public class RRC_SchedulingRequestResourceConfig extends RRC_Sequence {

    public RRC_SchedulingRequestResourceId schedulingRequestResourceId;
    public RRC_SchedulingRequestId schedulingRequestID;
    public RRC_SchedulingRequestResourceConfig__periodicityAndOffset periodicityAndOffset;
    public RRC_PUCCH_ResourceId resource;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingRequestResourceId","schedulingRequestID","periodicityAndOffset","resource" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingRequestResourceId","schedulingRequestID","periodicityAndOffset","resource" };
    }

    @Override
    public String getAsnName() {
        return "SchedulingRequestResourceConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SchedulingRequestResourceConfig";
    }

}

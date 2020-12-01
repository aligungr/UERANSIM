/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SchedulingRequestConfig__schedulingRequestToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SchedulingRequestConfig__schedulingRequestToReleaseList;

public class RRC_SchedulingRequestConfig extends RRC_Sequence {

    public RRC_SchedulingRequestConfig__schedulingRequestToAddModList schedulingRequestToAddModList;
    public RRC_SchedulingRequestConfig__schedulingRequestToReleaseList schedulingRequestToReleaseList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingRequestToAddModList","schedulingRequestToReleaseList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingRequestToAddModList","schedulingRequestToReleaseList" };
    }

    @Override
    public String getAsnName() {
        return "SchedulingRequestConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SchedulingRequestConfig";
    }

}

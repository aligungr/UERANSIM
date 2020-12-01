/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SI_SchedulingInfo__schedulingInfoList;

public class RRC_SI_SchedulingInfo extends RRC_Sequence {

    public RRC_SI_SchedulingInfo__schedulingInfoList schedulingInfoList;
    public RRC_Integer si_WindowLength;
    public RRC_SI_RequestConfig si_RequestConfig;
    public RRC_SI_RequestConfig si_RequestConfigSUL;
    public RRC_BitString systemInformationAreaID;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingInfoList","si-WindowLength","si-RequestConfig","si-RequestConfigSUL","systemInformationAreaID" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingInfoList","si_WindowLength","si_RequestConfig","si_RequestConfigSUL","systemInformationAreaID" };
    }

    @Override
    public String getAsnName() {
        return "SI-SchedulingInfo";
    }

    @Override
    public String getXmlTagName() {
        return "SI-SchedulingInfo";
    }

}

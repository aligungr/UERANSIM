/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultFreqList;

public class RRC_FailureReportSCG extends RRC_Sequence {

    public RRC_Integer failureType;
    public RRC_MeasResultFreqList measResultFreqList;
    public RRC_OctetString measResultSCG_Failure;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "failureType","measResultFreqList","measResultSCG-Failure" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "failureType","measResultFreqList","measResultSCG_Failure" };
    }

    @Override
    public String getAsnName() {
        return "FailureReportSCG";
    }

    @Override
    public String getXmlTagName() {
        return "FailureReportSCG";
    }

}

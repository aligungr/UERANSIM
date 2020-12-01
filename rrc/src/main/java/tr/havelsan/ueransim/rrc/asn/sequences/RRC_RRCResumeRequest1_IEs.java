/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ResumeCause;

public class RRC_RRCResumeRequest1_IEs extends RRC_Sequence {

    public RRC_I_RNTI_Value resumeIdentity;
    public RRC_BitString resumeMAC_I;
    public RRC_ResumeCause resumeCause;
    public RRC_BitString spare;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resumeIdentity","resumeMAC-I","resumeCause","spare" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resumeIdentity","resumeMAC_I","resumeCause","spare" };
    }

    @Override
    public String getAsnName() {
        return "RRCResumeRequest1-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCResumeRequest1-IEs";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_CapabilityRAT_RequestList;

public class RRC_UECapabilityEnquiry_IEs extends RRC_Sequence {

    public RRC_UE_CapabilityRAT_RequestList ue_CapabilityRAT_RequestList;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_OctetString ue_CapabilityEnquiryExt;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-CapabilityRAT-RequestList","lateNonCriticalExtension","ue-CapabilityEnquiryExt" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_CapabilityRAT_RequestList","lateNonCriticalExtension","ue_CapabilityEnquiryExt" };
    }

    @Override
    public String getAsnName() {
        return "UECapabilityEnquiry-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "UECapabilityEnquiry-IEs";
    }

}

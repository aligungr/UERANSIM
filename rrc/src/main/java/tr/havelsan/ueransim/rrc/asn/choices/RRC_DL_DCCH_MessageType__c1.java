/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_DL_DCCH_MessageType__c1 extends RRC_Choice {

    public RRC_RRCReconfiguration rrcReconfiguration;
    public RRC_RRCResume rrcResume;
    public RRC_RRCRelease rrcRelease;
    public RRC_RRCReestablishment rrcReestablishment;
    public RRC_SecurityModeCommand securityModeCommand;
    public RRC_DLInformationTransfer dlInformationTransfer;
    public RRC_UECapabilityEnquiry ueCapabilityEnquiry;
    public RRC_CounterCheck counterCheck;
    public RRC_MobilityFromNRCommand mobilityFromNRCommand;
    public RRC_Null spare7;
    public RRC_Null spare6;
    public RRC_Null spare5;
    public RRC_Null spare4;
    public RRC_Null spare3;
    public RRC_Null spare2;
    public RRC_Null spare1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcReconfiguration","rrcResume","rrcRelease","rrcReestablishment","securityModeCommand","dlInformationTransfer","ueCapabilityEnquiry","counterCheck","mobilityFromNRCommand","spare7","spare6","spare5","spare4","spare3","spare2","spare1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcReconfiguration","rrcResume","rrcRelease","rrcReestablishment","securityModeCommand","dlInformationTransfer","ueCapabilityEnquiry","counterCheck","mobilityFromNRCommand","spare7","spare6","spare5","spare4","spare3","spare2","spare1" };
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

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCReestablishmentRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCResumeRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSetupRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSystemInfoRequest;

public class RRC_UL_CCCH_MessageType__c1 extends RRC_Choice {

    public RRC_RRCSetupRequest rrcSetupRequest;
    public RRC_RRCResumeRequest rrcResumeRequest;
    public RRC_RRCReestablishmentRequest rrcReestablishmentRequest;
    public RRC_RRCSystemInfoRequest rrcSystemInfoRequest;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrcSetupRequest","rrcResumeRequest","rrcReestablishmentRequest","rrcSystemInfoRequest" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrcSetupRequest","rrcResumeRequest","rrcReestablishmentRequest","rrcSystemInfoRequest" };
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

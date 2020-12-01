/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UERadioPagingInformation_IEs;

public class RRC_UERadioPagingInformation__criticalExtensions__c1 extends RRC_Choice {

    public RRC_UERadioPagingInformation_IEs ueRadioPagingInformation;
    public RRC_Null spare7;
    public RRC_Null spare6;
    public RRC_Null spare5;
    public RRC_Null spare4;
    public RRC_Null spare3;
    public RRC_Null spare2;
    public RRC_Null spare1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ueRadioPagingInformation","spare7","spare6","spare5","spare4","spare3","spare2","spare1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ueRadioPagingInformation","spare7","spare6","spare5","spare4","spare3","spare2","spare1" };
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

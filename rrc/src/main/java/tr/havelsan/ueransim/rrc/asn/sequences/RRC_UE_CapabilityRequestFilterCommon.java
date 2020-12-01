/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_CapabilityRequestFilterCommon extends RRC_Sequence {

    public RRC_UE_CapabilityRequestFilterCommon__mrdc_Request mrdc_Request;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mrdc-Request" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mrdc_Request" };
    }

    @Override
    public String getAsnName() {
        return "UE-CapabilityRequestFilterCommon";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRequestFilterCommon";
    }

}

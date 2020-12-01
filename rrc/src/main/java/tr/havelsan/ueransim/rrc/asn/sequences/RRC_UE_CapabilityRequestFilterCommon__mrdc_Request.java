/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_CapabilityRequestFilterCommon__mrdc_Request extends RRC_Sequence {

    public RRC_Integer omitEN_DC;
    public RRC_Integer includeNR_DC;
    public RRC_Integer includeNE_DC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "omitEN-DC","includeNR-DC","includeNE-DC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "omitEN_DC","includeNR_DC","includeNE_DC" };
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

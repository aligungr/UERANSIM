/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_TDD_UL_DL_Pattern__ext1 extends RRC_Sequence {

    public RRC_Integer dl_UL_TransmissionPeriodicity_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dl-UL-TransmissionPeriodicity-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dl_UL_TransmissionPeriodicity_v1530" };
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

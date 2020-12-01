/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NumberOfCarriers;

public class RRC_ProcessingParameters__differentTB_PerSlot extends RRC_Sequence {

    public RRC_NumberOfCarriers upto1;
    public RRC_NumberOfCarriers upto2;
    public RRC_NumberOfCarriers upto4;
    public RRC_NumberOfCarriers upto7;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "upto1","upto2","upto4","upto7" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "upto1","upto2","upto4","upto7" };
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

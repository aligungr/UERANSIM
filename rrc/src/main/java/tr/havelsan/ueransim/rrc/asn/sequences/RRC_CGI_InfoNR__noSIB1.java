/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CGI_InfoNR__noSIB1 extends RRC_Sequence {

    public RRC_Integer ssb_SubcarrierOffset;
    public RRC_PDCCH_ConfigSIB1 pdcch_ConfigSIB1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-SubcarrierOffset","pdcch-ConfigSIB1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_SubcarrierOffset","pdcch_ConfigSIB1" };
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

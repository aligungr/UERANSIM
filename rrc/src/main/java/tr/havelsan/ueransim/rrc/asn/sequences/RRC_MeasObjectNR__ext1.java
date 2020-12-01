/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_MeasObjectNR__ext1 extends RRC_Sequence {

    public RRC_FreqBandIndicatorNR freqBandIndicatorNR_v1530;
    public RRC_Integer measCycleSCell_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "freqBandIndicatorNR-v1530","measCycleSCell-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "freqBandIndicatorNR_v1530","measCycleSCell_v1530" };
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

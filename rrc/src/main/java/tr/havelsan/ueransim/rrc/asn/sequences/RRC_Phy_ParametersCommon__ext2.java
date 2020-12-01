/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersCommon__ext2 extends RRC_Sequence {

    public RRC_Integer maxNumberSearchSpaces;
    public RRC_Integer rateMatchingCtrlResrcSetDynamic;
    public RRC_Integer maxLayersMIMO_Indication;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSearchSpaces","rateMatchingCtrlResrcSetDynamic","maxLayersMIMO-Indication" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSearchSpaces","rateMatchingCtrlResrcSetDynamic","maxLayersMIMO_Indication" };
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

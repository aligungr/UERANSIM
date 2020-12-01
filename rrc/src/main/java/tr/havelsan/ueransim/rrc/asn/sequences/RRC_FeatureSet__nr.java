/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetDownlinkId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetUplinkId;

public class RRC_FeatureSet__nr extends RRC_Sequence {

    public RRC_FeatureSetDownlinkId downlinkSetNR;
    public RRC_FeatureSetUplinkId uplinkSetNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "downlinkSetNR","uplinkSetNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "downlinkSetNR","uplinkSetNR" };
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

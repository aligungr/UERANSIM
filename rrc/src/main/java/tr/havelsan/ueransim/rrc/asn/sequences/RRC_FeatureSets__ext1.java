/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__ext1__featureSetsDownlink_v1540;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__ext1__featureSetsUplinkPerCC_v1540;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__ext1__featureSetsUplink_v1540;

public class RRC_FeatureSets__ext1 extends RRC_Sequence {

    public RRC_FeatureSets__ext1__featureSetsDownlink_v1540 featureSetsDownlink_v1540;
    public RRC_FeatureSets__ext1__featureSetsUplink_v1540 featureSetsUplink_v1540;
    public RRC_FeatureSets__ext1__featureSetsUplinkPerCC_v1540 featureSetsUplinkPerCC_v1540;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "featureSetsDownlink-v1540","featureSetsUplink-v1540","featureSetsUplinkPerCC-v1540" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "featureSetsDownlink_v1540","featureSetsUplink_v1540","featureSetsUplinkPerCC_v1540" };
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

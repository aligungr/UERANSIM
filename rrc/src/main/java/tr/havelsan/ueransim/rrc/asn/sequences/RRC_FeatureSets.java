/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__featureSetsDownlink;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__featureSetsDownlinkPerCC;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__featureSetsUplink;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSets__featureSetsUplinkPerCC;

public class RRC_FeatureSets extends RRC_Sequence {

    public RRC_FeatureSets__featureSetsDownlink featureSetsDownlink;
    public RRC_FeatureSets__featureSetsDownlinkPerCC featureSetsDownlinkPerCC;
    public RRC_FeatureSets__featureSetsUplink featureSetsUplink;
    public RRC_FeatureSets__featureSetsUplinkPerCC featureSetsUplinkPerCC;
    public RRC_FeatureSets__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "featureSetsDownlink","featureSetsDownlinkPerCC","featureSetsUplink","featureSetsUplinkPerCC","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "featureSetsDownlink","featureSetsDownlinkPerCC","featureSetsUplink","featureSetsUplinkPerCC","ext1" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSets";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSets";
    }

}

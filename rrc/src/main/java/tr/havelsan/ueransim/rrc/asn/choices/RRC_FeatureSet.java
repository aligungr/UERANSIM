/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FeatureSet__eutra;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FeatureSet__nr;

public class RRC_FeatureSet extends RRC_Choice {

    public RRC_FeatureSet__eutra eutra;
    public RRC_FeatureSet__nr nr;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra","nr" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra","nr" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSet";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSet";
    }

}

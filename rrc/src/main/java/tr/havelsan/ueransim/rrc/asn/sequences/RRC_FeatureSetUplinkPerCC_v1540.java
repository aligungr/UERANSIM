/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetUplinkPerCC_v1540 extends RRC_Sequence {

    public RRC_FeatureSetUplinkPerCC_v1540__mimo_NonCB_PUSCH mimo_NonCB_PUSCH;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mimo-NonCB-PUSCH" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mimo_NonCB_PUSCH" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetUplinkPerCC-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetUplinkPerCC-v1540";
    }

}

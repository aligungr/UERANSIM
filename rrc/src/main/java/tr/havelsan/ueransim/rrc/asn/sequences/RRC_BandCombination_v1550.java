/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandCombination_v1550 extends RRC_Sequence {

    public RRC_CA_ParametersNR_v1550 ca_ParametersNR_v1550;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ca-ParametersNR-v1550" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ca_ParametersNR_v1550" };
    }

    @Override
    public String getAsnName() {
        return "BandCombination-v1550";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombination-v1550";
    }

}

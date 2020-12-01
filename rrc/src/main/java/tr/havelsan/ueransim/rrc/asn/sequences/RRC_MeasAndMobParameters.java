/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParameters extends RRC_Sequence {

    public RRC_MeasAndMobParametersCommon measAndMobParametersCommon;
    public RRC_MeasAndMobParametersXDD_Diff measAndMobParametersXDD_Diff;
    public RRC_MeasAndMobParametersFRX_Diff measAndMobParametersFRX_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measAndMobParametersCommon","measAndMobParametersXDD-Diff","measAndMobParametersFRX-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measAndMobParametersCommon","measAndMobParametersXDD_Diff","measAndMobParametersFRX_Diff" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParameters";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParameters";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersMRDC extends RRC_Sequence {

    public RRC_MeasAndMobParametersMRDC_Common measAndMobParametersMRDC_Common;
    public RRC_MeasAndMobParametersMRDC_XDD_Diff measAndMobParametersMRDC_XDD_Diff;
    public RRC_MeasAndMobParametersMRDC_FRX_Diff measAndMobParametersMRDC_FRX_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measAndMobParametersMRDC-Common","measAndMobParametersMRDC-XDD-Diff","measAndMobParametersMRDC-FRX-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measAndMobParametersMRDC_Common","measAndMobParametersMRDC_XDD_Diff","measAndMobParametersMRDC_FRX_Diff" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersMRDC";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersMRDC";
    }

}

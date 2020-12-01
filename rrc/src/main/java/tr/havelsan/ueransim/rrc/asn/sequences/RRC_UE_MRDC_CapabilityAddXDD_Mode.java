/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_MRDC_CapabilityAddXDD_Mode extends RRC_Sequence {

    public RRC_MeasAndMobParametersMRDC_XDD_Diff measAndMobParametersMRDC_XDD_Diff;
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersMRDC_XDD_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measAndMobParametersMRDC-XDD-Diff","generalParametersMRDC-XDD-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measAndMobParametersMRDC_XDD_Diff","generalParametersMRDC_XDD_Diff" };
    }

    @Override
    public String getAsnName() {
        return "UE-MRDC-CapabilityAddXDD-Mode";
    }

    @Override
    public String getXmlTagName() {
        return "UE-MRDC-CapabilityAddXDD-Mode";
    }

}

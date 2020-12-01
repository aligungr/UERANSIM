/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_NRDC_Parameters extends RRC_Sequence {

    public RRC_MeasAndMobParametersMRDC measAndMobParametersNRDC;
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersNRDC;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode fdd_Add_UE_NRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode tdd_Add_UE_NRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr1_Add_UE_NRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr2_Add_UE_NRDC_Capabilities;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_NRDC_Parameters__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measAndMobParametersNRDC","generalParametersNRDC","fdd-Add-UE-NRDC-Capabilities","tdd-Add-UE-NRDC-Capabilities","fr1-Add-UE-NRDC-Capabilities","fr2-Add-UE-NRDC-Capabilities","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measAndMobParametersNRDC","generalParametersNRDC","fdd_Add_UE_NRDC_Capabilities","tdd_Add_UE_NRDC_Capabilities","fr1_Add_UE_NRDC_Capabilities","fr2_Add_UE_NRDC_Capabilities","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "NRDC-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "NRDC-Parameters";
    }

}

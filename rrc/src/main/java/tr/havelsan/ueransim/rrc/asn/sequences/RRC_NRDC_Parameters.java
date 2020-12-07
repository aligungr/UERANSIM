/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_NRDC_Parameters extends AsnSequence {
    public RRC_MeasAndMobParametersMRDC measAndMobParametersNRDC; // optional
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersNRDC; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode fdd_Add_UE_NRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode tdd_Add_UE_NRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr1_Add_UE_NRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr2_Add_UE_NRDC_Capabilities; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_40 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_40 extends AsnSequence {
    }
}


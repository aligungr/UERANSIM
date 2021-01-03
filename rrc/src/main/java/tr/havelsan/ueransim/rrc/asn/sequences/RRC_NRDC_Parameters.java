/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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


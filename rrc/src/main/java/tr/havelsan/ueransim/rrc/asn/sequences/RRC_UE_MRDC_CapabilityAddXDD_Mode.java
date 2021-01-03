/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_MRDC_CapabilityAddXDD_Mode extends AsnSequence {
    public RRC_MeasAndMobParametersMRDC_XDD_Diff measAndMobParametersMRDC_XDD_Diff; // optional
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersMRDC_XDD_Diff; // optional
}


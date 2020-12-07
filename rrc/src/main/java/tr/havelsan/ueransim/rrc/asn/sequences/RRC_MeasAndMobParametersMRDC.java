/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasAndMobParametersMRDC extends AsnSequence {
    public RRC_MeasAndMobParametersMRDC_Common measAndMobParametersMRDC_Common; // optional
    public RRC_MeasAndMobParametersMRDC_XDD_Diff measAndMobParametersMRDC_XDD_Diff; // optional
    public RRC_MeasAndMobParametersMRDC_FRX_Diff measAndMobParametersMRDC_FRX_Diff; // optional
}


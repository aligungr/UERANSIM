/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_EUTRA_RSTD_Info extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA carrierFreq; // mandatory
    public AsnInteger measPRS_Offset; // mandatory, VALUE(0..39)
}


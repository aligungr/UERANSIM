/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_MeasResult2EUTRA extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA carrierFreq; // mandatory
    public RRC_MeasResultEUTRA measResultServingCell; // optional
    public RRC_MeasResultEUTRA measResultBestNeighCell; // optional
}


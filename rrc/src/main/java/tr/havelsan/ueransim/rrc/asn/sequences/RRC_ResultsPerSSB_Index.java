/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_ResultsPerSSB_Index extends AsnSequence {
    public RRC_SSB_Index ssb_Index; // mandatory
    public RRC_MeasQuantityResults ssb_Results; // optional
}


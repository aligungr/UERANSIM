/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultListNR;

public class RRC_MeasResult2NR extends AsnSequence {
    public RRC_ARFCN_ValueNR ssbFrequency; // optional
    public RRC_ARFCN_ValueNR refFreqCSI_RS; // optional
    public RRC_MeasResultNR measResultServingCell; // optional
    public RRC_MeasResultListNR measResultNeighCellListNR; // optional
}


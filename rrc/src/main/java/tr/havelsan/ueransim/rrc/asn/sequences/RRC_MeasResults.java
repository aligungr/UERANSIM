/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_MeasResults extends AsnSequence {
    public RRC_MeasId measId; // mandatory
    public RRC_MeasResultServMOList measResultServingMOList; // mandatory
    public RRC_measResultNeighCells measResultNeighCells; // optional
    public RRC_ext1_35 ext1; // optional

    public static class RRC_measResultNeighCells extends AsnChoice {
        public RRC_MeasResultListNR measResultListNR;
        public RRC_MeasResultListEUTRA measResultListEUTRA;
    }

    public static class RRC_ext1_35 extends AsnSequence {
        public RRC_MeasResultServFreqListEUTRA_SCG measResultServFreqListEUTRA_SCG; // optional
        public RRC_MeasResultServFreqListNR_SCG measResultServFreqListNR_SCG; // optional
        public RRC_MeasResultSFTD_EUTRA measResultSFTD_EUTRA; // optional
        public RRC_MeasResultCellSFTD_NR measResultSFTD_NR; // optional
    }
}


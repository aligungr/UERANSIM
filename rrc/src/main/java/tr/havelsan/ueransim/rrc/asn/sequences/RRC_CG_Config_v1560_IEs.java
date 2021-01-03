/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CandidateServingFreqListEUTRA;

public class RRC_CG_Config_v1560_IEs extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA pSCellFrequencyEUTRA; // optional
    public AsnOctetString scg_CellGroupConfigEUTRA; // optional
    public AsnOctetString candidateCellInfoListSN_EUTRA; // optional
    public RRC_CandidateServingFreqListEUTRA candidateServingFreqListEUTRA; // optional
    public RRC_needForGaps needForGaps; // optional
    public RRC_DRX_Config drx_ConfigSCG; // optional
    public RRC_reportCGI_RequestEUTRA reportCGI_RequestEUTRA; // optional
    public RRC_nonCriticalExtension_30 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_30 extends AsnSequence {
    }

    public static class RRC_reportCGI_RequestEUTRA extends AsnSequence {
        public RRC_requestedCellInfoEUTRA requestedCellInfoEUTRA; // optional
    
        public static class RRC_requestedCellInfoEUTRA extends AsnSequence {
            public RRC_ARFCN_ValueEUTRA eutraFrequency; // mandatory
            public RRC_EUTRA_PhysCellId cellForWhichToReportCGI_EUTRA; // mandatory
        }
    }

    public static class RRC_needForGaps extends AsnEnumerated {
        public static final RRC_needForGaps TRUE = new RRC_needForGaps(0);
    
        private RRC_needForGaps(long value) {
            super(value);
        }
    }
}


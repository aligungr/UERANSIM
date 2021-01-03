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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FR_InfoList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultCellListSFTD_EUTRA;

public class RRC_CG_ConfigInfo_v1560_IEs extends AsnSequence {
    public AsnOctetString candidateCellInfoListMN_EUTRA; // optional
    public AsnOctetString candidateCellInfoListSN_EUTRA; // optional
    public AsnOctetString sourceConfigSCG_EUTRA; // optional
    public RRC_scgFailureInfoEUTRA scgFailureInfoEUTRA; // optional
    public RRC_DRX_Config drx_ConfigMCG; // optional
    public RRC_measResultReportCGI_EUTRA measResultReportCGI_EUTRA; // optional
    public RRC_MeasResultCellListSFTD_EUTRA measResultCellListSFTD_EUTRA; // optional
    public RRC_FR_InfoList fr_InfoListMCG; // optional
    public RRC_nonCriticalExtension_29 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_29 extends AsnSequence {
    }

    public static class RRC_scgFailureInfoEUTRA extends AsnSequence {
        public RRC_failureTypeEUTRA failureTypeEUTRA; // mandatory
        public AsnOctetString measResultSCG_EUTRA; // mandatory
    
        public static class RRC_failureTypeEUTRA extends AsnEnumerated {
            public static final RRC_failureTypeEUTRA T313_EXPIRY = new RRC_failureTypeEUTRA(0);
            public static final RRC_failureTypeEUTRA RANDOMACCESSPROBLEM = new RRC_failureTypeEUTRA(1);
            public static final RRC_failureTypeEUTRA RLC_MAXNUMRETX = new RRC_failureTypeEUTRA(2);
            public static final RRC_failureTypeEUTRA SCG_CHANGEFAILURE = new RRC_failureTypeEUTRA(3);
        
            private RRC_failureTypeEUTRA(long value) {
                super(value);
            }
        }
    }

    public static class RRC_measResultReportCGI_EUTRA extends AsnSequence {
        public RRC_ARFCN_ValueEUTRA eutraFrequency; // mandatory
        public RRC_EUTRA_PhysCellId cellForWhichToReportCGI_EUTRA; // mandatory
        public RRC_CGI_InfoEUTRA cgi_InfoEUTRA; // mandatory
    }
}


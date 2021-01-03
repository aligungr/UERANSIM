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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultCellListSFTD_NR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultList2NR;

public class RRC_CG_ConfigInfo_IEs extends AsnSequence {
    public AsnOctetString ue_CapabilityInfo; // optional, SIZE(0..MAX)
    public RRC_MeasResultList2NR candidateCellInfoListMN; // optional
    public AsnOctetString candidateCellInfoListSN; // optional, SIZE(0..MAX)
    public RRC_MeasResultCellListSFTD_NR measResultCellListSFTD_NR; // optional
    public RRC_scgFailureInfo scgFailureInfo; // optional
    public RRC_ConfigRestrictInfoSCG configRestrictInfo; // optional
    public RRC_DRX_Info drx_InfoMCG; // optional
    public RRC_MeasConfigMN measConfigMN; // optional
    public AsnOctetString sourceConfigSCG; // optional, SIZE(0..MAX)
    public AsnOctetString scg_RB_Config; // optional, SIZE(0..MAX)
    public AsnOctetString mcg_RB_Config; // optional, SIZE(0..MAX)
    public RRC_MRDC_AssistanceInfo mrdc_AssistanceInfo; // optional
    public RRC_CG_ConfigInfo_v1540_IEs nonCriticalExtension; // optional

    public static class RRC_scgFailureInfo extends AsnSequence {
        public RRC_failureType_4 failureType; // mandatory
        public AsnOctetString measResultSCG; // mandatory, SIZE(0..MAX)
    
        public static class RRC_failureType_4 extends AsnEnumerated {
            public static final RRC_failureType_4 T310_EXPIRY = new RRC_failureType_4(0);
            public static final RRC_failureType_4 RANDOMACCESSPROBLEM = new RRC_failureType_4(1);
            public static final RRC_failureType_4 RLC_MAXNUMRETX = new RRC_failureType_4(2);
            public static final RRC_failureType_4 SYNCHRECONFIGFAILURE_SCG = new RRC_failureType_4(3);
            public static final RRC_failureType_4 SCG_RECONFIGFAILURE = new RRC_failureType_4(4);
            public static final RRC_failureType_4 SRB3_INTEGRITYFAILURE = new RRC_failureType_4(5);
        
            private RRC_failureType_4(long value) {
                super(value);
            }
        }
    }
}


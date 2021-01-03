/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CandidateServingFreqListNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FR_InfoList;

public class RRC_CG_Config_IEs extends AsnSequence {
    public AsnOctetString scg_CellGroupConfig; // optional, SIZE(0..MAX)
    public AsnOctetString scg_RB_Config; // optional, SIZE(0..MAX)
    public RRC_ConfigRestrictModReqSCG configRestrictModReq; // optional
    public RRC_DRX_Info drx_InfoSCG; // optional
    public AsnOctetString candidateCellInfoListSN; // optional, SIZE(0..MAX)
    public RRC_MeasConfigSN measConfigSN; // optional
    public RRC_BandCombinationInfoSN selectedBandCombination; // optional
    public RRC_FR_InfoList fr_InfoListSCG; // optional
    public RRC_CandidateServingFreqListNR candidateServingFreqListNR; // optional
    public RRC_CG_Config_v1540_IEs nonCriticalExtension; // optional
}


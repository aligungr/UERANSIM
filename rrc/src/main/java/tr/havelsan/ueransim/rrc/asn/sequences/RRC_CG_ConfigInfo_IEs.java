/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultCellListSFTD_NR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultList2NR;

public class RRC_CG_ConfigInfo_IEs extends RRC_Sequence {

    public RRC_OctetString ue_CapabilityInfo;
    public RRC_MeasResultList2NR candidateCellInfoListMN;
    public RRC_OctetString candidateCellInfoListSN;
    public RRC_MeasResultCellListSFTD_NR measResultCellListSFTD_NR;
    public RRC_CG_ConfigInfo_IEs__scgFailureInfo scgFailureInfo;
    public RRC_ConfigRestrictInfoSCG configRestrictInfo;
    public RRC_DRX_Info drx_InfoMCG;
    public RRC_MeasConfigMN measConfigMN;
    public RRC_OctetString sourceConfigSCG;
    public RRC_OctetString scg_RB_Config;
    public RRC_OctetString mcg_RB_Config;
    public RRC_MRDC_AssistanceInfo mrdc_AssistanceInfo;
    public RRC_CG_ConfigInfo_v1540_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-CapabilityInfo","candidateCellInfoListMN","candidateCellInfoListSN","measResultCellListSFTD-NR","scgFailureInfo","configRestrictInfo","drx-InfoMCG","measConfigMN","sourceConfigSCG","scg-RB-Config","mcg-RB-Config","mrdc-AssistanceInfo","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_CapabilityInfo","candidateCellInfoListMN","candidateCellInfoListSN","measResultCellListSFTD_NR","scgFailureInfo","configRestrictInfo","drx_InfoMCG","measConfigMN","sourceConfigSCG","scg_RB_Config","mcg_RB_Config","mrdc_AssistanceInfo","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-ConfigInfo-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-ConfigInfo-IEs";
    }

}

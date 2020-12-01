/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FR_InfoList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultCellListSFTD_EUTRA;

public class RRC_CG_ConfigInfo_v1560_IEs extends RRC_Sequence {

    public RRC_OctetString candidateCellInfoListMN_EUTRA;
    public RRC_OctetString candidateCellInfoListSN_EUTRA;
    public RRC_OctetString sourceConfigSCG_EUTRA;
    public RRC_CG_ConfigInfo_v1560_IEs__scgFailureInfoEUTRA scgFailureInfoEUTRA;
    public RRC_DRX_Config drx_ConfigMCG;
    public RRC_CG_ConfigInfo_v1560_IEs__measResultReportCGI_EUTRA measResultReportCGI_EUTRA;
    public RRC_MeasResultCellListSFTD_EUTRA measResultCellListSFTD_EUTRA;
    public RRC_FR_InfoList fr_InfoListMCG;
    public RRC_CG_ConfigInfo_v1560_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "candidateCellInfoListMN-EUTRA","candidateCellInfoListSN-EUTRA","sourceConfigSCG-EUTRA","scgFailureInfoEUTRA","drx-ConfigMCG","measResultReportCGI-EUTRA","measResultCellListSFTD-EUTRA","fr-InfoListMCG","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "candidateCellInfoListMN_EUTRA","candidateCellInfoListSN_EUTRA","sourceConfigSCG_EUTRA","scgFailureInfoEUTRA","drx_ConfigMCG","measResultReportCGI_EUTRA","measResultCellListSFTD_EUTRA","fr_InfoListMCG","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-ConfigInfo-v1560-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-ConfigInfo-v1560-IEs";
    }

}

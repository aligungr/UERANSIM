/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PH_TypeListSCG;

public class RRC_CG_Config_v1540_IEs extends RRC_Sequence {

    public RRC_ARFCN_ValueNR pSCellFrequency;
    public RRC_CG_Config_v1540_IEs__reportCGI_RequestNR reportCGI_RequestNR;
    public RRC_PH_TypeListSCG ph_InfoSCG;
    public RRC_CG_Config_v1560_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pSCellFrequency","reportCGI-RequestNR","ph-InfoSCG","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pSCellFrequency","reportCGI_RequestNR","ph_InfoSCG","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-Config-v1540-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-Config-v1540-IEs";
    }

}

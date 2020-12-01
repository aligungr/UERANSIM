/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PH_TypeListMCG;

public class RRC_CG_ConfigInfo_v1540_IEs extends RRC_Sequence {

    public RRC_PH_TypeListMCG ph_InfoMCG;
    public RRC_CG_ConfigInfo_v1540_IEs__measResultReportCGI measResultReportCGI;
    public RRC_CG_ConfigInfo_v1560_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ph-InfoMCG","measResultReportCGI","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ph_InfoMCG","measResultReportCGI","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CG-ConfigInfo-v1540-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CG-ConfigInfo-v1540-IEs";
    }

}

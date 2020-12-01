/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_CapabilityRAT_ContainerList;

public class RRC_HandoverPreparationInformation_IEs extends RRC_Sequence {

    public RRC_UE_CapabilityRAT_ContainerList ue_CapabilityRAT_List;
    public RRC_AS_Config sourceConfig;
    public RRC_RRM_Config rrm_Config;
    public RRC_AS_Context as_Context;
    public RRC_HandoverPreparationInformation_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-CapabilityRAT-List","sourceConfig","rrm-Config","as-Context","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_CapabilityRAT_List","sourceConfig","rrm_Config","as_Context","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "HandoverPreparationInformation-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverPreparationInformation-IEs";
    }

}

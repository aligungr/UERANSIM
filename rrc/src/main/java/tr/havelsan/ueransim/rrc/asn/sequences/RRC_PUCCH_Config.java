/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUCCH_FormatConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_PUCCH_Config extends RRC_Sequence {

    public RRC_PUCCH_Config__resourceSetToAddModList resourceSetToAddModList;
    public RRC_PUCCH_Config__resourceSetToReleaseList resourceSetToReleaseList;
    public RRC_PUCCH_Config__resourceToAddModList resourceToAddModList;
    public RRC_PUCCH_Config__resourceToReleaseList resourceToReleaseList;
    public RRC_SetupRelease_PUCCH_FormatConfig format1;
    public RRC_SetupRelease_PUCCH_FormatConfig format2;
    public RRC_SetupRelease_PUCCH_FormatConfig format3;
    public RRC_SetupRelease_PUCCH_FormatConfig format4;
    public RRC_PUCCH_Config__schedulingRequestResourceToAddModList schedulingRequestResourceToAddModList;
    public RRC_PUCCH_Config__schedulingRequestResourceToReleaseList schedulingRequestResourceToReleaseList;
    public RRC_PUCCH_Config__multi_CSI_PUCCH_ResourceList multi_CSI_PUCCH_ResourceList;
    public RRC_PUCCH_Config__dl_DataToUL_ACK dl_DataToUL_ACK;
    public RRC_PUCCH_Config__spatialRelationInfoToAddModList spatialRelationInfoToAddModList;
    public RRC_PUCCH_Config__spatialRelationInfoToReleaseList spatialRelationInfoToReleaseList;
    public RRC_PUCCH_PowerControl pucch_PowerControl;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resourceSetToAddModList","resourceSetToReleaseList","resourceToAddModList","resourceToReleaseList","format1","format2","format3","format4","schedulingRequestResourceToAddModList","schedulingRequestResourceToReleaseList","multi-CSI-PUCCH-ResourceList","dl-DataToUL-ACK","spatialRelationInfoToAddModList","spatialRelationInfoToReleaseList","pucch-PowerControl" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resourceSetToAddModList","resourceSetToReleaseList","resourceToAddModList","resourceToReleaseList","format1","format2","format3","format4","schedulingRequestResourceToAddModList","schedulingRequestResourceToReleaseList","multi_CSI_PUCCH_ResourceList","dl_DataToUL_ACK","spatialRelationInfoToAddModList","spatialRelationInfoToReleaseList","pucch_PowerControl" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-Config";
    }

}

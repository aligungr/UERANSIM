/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB1 extends RRC_Sequence {

    public RRC_SIB1__cellSelectionInfo cellSelectionInfo;
    public RRC_CellAccessRelatedInfo cellAccessRelatedInfo;
    public RRC_ConnEstFailureControl connEstFailureControl;
    public RRC_SI_SchedulingInfo si_SchedulingInfo;
    public RRC_ServingCellConfigCommonSIB servingCellConfigCommon;
    public RRC_Integer ims_EmergencySupport;
    public RRC_Integer eCallOverIMS_Support;
    public RRC_UE_TimersAndConstants ue_TimersAndConstants;
    public RRC_SIB1__uac_BarringInfo uac_BarringInfo;
    public RRC_Integer useFullResumeID;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_SIB1__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellSelectionInfo","cellAccessRelatedInfo","connEstFailureControl","si-SchedulingInfo","servingCellConfigCommon","ims-EmergencySupport","eCallOverIMS-Support","ue-TimersAndConstants","uac-BarringInfo","useFullResumeID","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellSelectionInfo","cellAccessRelatedInfo","connEstFailureControl","si_SchedulingInfo","servingCellConfigCommon","ims_EmergencySupport","eCallOverIMS_Support","ue_TimersAndConstants","uac_BarringInfo","useFullResumeID","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SIB1";
    }

    @Override
    public String getXmlTagName() {
        return "SIB1";
    }

}

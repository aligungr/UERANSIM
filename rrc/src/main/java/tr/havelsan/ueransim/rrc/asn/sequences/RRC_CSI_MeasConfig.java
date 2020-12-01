/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_CSI_AperiodicTriggerStateList;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_CSI_SemiPersistentOnPUSCH_TriggerStateList;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_CSI_MeasConfig extends RRC_Sequence {

    public RRC_CSI_MeasConfig__nzp_CSI_RS_ResourceToAddModList nzp_CSI_RS_ResourceToAddModList;
    public RRC_CSI_MeasConfig__nzp_CSI_RS_ResourceToReleaseList nzp_CSI_RS_ResourceToReleaseList;
    public RRC_CSI_MeasConfig__nzp_CSI_RS_ResourceSetToAddModList nzp_CSI_RS_ResourceSetToAddModList;
    public RRC_CSI_MeasConfig__nzp_CSI_RS_ResourceSetToReleaseList nzp_CSI_RS_ResourceSetToReleaseList;
    public RRC_CSI_MeasConfig__csi_IM_ResourceToAddModList csi_IM_ResourceToAddModList;
    public RRC_CSI_MeasConfig__csi_IM_ResourceToReleaseList csi_IM_ResourceToReleaseList;
    public RRC_CSI_MeasConfig__csi_IM_ResourceSetToAddModList csi_IM_ResourceSetToAddModList;
    public RRC_CSI_MeasConfig__csi_IM_ResourceSetToReleaseList csi_IM_ResourceSetToReleaseList;
    public RRC_CSI_MeasConfig__csi_SSB_ResourceSetToAddModList csi_SSB_ResourceSetToAddModList;
    public RRC_CSI_MeasConfig__csi_SSB_ResourceSetToReleaseList csi_SSB_ResourceSetToReleaseList;
    public RRC_CSI_MeasConfig__csi_ResourceConfigToAddModList csi_ResourceConfigToAddModList;
    public RRC_CSI_MeasConfig__csi_ResourceConfigToReleaseList csi_ResourceConfigToReleaseList;
    public RRC_CSI_MeasConfig__csi_ReportConfigToAddModList csi_ReportConfigToAddModList;
    public RRC_CSI_MeasConfig__csi_ReportConfigToReleaseList csi_ReportConfigToReleaseList;
    public RRC_Integer reportTriggerSize;
    public RRC_SetupRelease_CSI_AperiodicTriggerStateList aperiodicTriggerStateList;
    public RRC_SetupRelease_CSI_SemiPersistentOnPUSCH_TriggerStateList semiPersistentOnPUSCH_TriggerStateList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-RS-ResourceToAddModList","nzp-CSI-RS-ResourceToReleaseList","nzp-CSI-RS-ResourceSetToAddModList","nzp-CSI-RS-ResourceSetToReleaseList","csi-IM-ResourceToAddModList","csi-IM-ResourceToReleaseList","csi-IM-ResourceSetToAddModList","csi-IM-ResourceSetToReleaseList","csi-SSB-ResourceSetToAddModList","csi-SSB-ResourceSetToReleaseList","csi-ResourceConfigToAddModList","csi-ResourceConfigToReleaseList","csi-ReportConfigToAddModList","csi-ReportConfigToReleaseList","reportTriggerSize","aperiodicTriggerStateList","semiPersistentOnPUSCH-TriggerStateList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_RS_ResourceToAddModList","nzp_CSI_RS_ResourceToReleaseList","nzp_CSI_RS_ResourceSetToAddModList","nzp_CSI_RS_ResourceSetToReleaseList","csi_IM_ResourceToAddModList","csi_IM_ResourceToReleaseList","csi_IM_ResourceSetToAddModList","csi_IM_ResourceSetToReleaseList","csi_SSB_ResourceSetToAddModList","csi_SSB_ResourceSetToReleaseList","csi_ResourceConfigToAddModList","csi_ResourceConfigToReleaseList","csi_ReportConfigToAddModList","csi_ReportConfigToReleaseList","reportTriggerSize","aperiodicTriggerStateList","semiPersistentOnPUSCH_TriggerStateList" };
    }

    @Override
    public String getAsnName() {
        return "CSI-MeasConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-MeasConfig";
    }

}

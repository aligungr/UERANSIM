/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_CSI_MeasConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDCCH_ServingCellConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDSCH_ServingCellConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TAG_Id;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfig__downlinkBWP_ToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfig__downlinkBWP_ToReleaseList;

public class RRC_ServingCellConfig extends RRC_Sequence {

    public RRC_TDD_UL_DL_ConfigDedicated tdd_UL_DL_ConfigurationDedicated;
    public RRC_BWP_DownlinkDedicated initialDownlinkBWP;
    public RRC_ServingCellConfig__downlinkBWP_ToReleaseList downlinkBWP_ToReleaseList;
    public RRC_ServingCellConfig__downlinkBWP_ToAddModList downlinkBWP_ToAddModList;
    public RRC_BWP_Id firstActiveDownlinkBWP_Id;
    public RRC_Integer bwp_InactivityTimer;
    public RRC_BWP_Id defaultDownlinkBWP_Id;
    public RRC_UplinkConfig uplinkConfig;
    public RRC_UplinkConfig supplementaryUplink;
    public RRC_SetupRelease_PDCCH_ServingCellConfig pdcch_ServingCellConfig;
    public RRC_SetupRelease_PDSCH_ServingCellConfig pdsch_ServingCellConfig;
    public RRC_SetupRelease_CSI_MeasConfig csi_MeasConfig;
    public RRC_Integer sCellDeactivationTimer;
    public RRC_CrossCarrierSchedulingConfig crossCarrierSchedulingConfig;
    public RRC_TAG_Id tag_Id;
    public RRC_Integer dummy;
    public RRC_Integer pathlossReferenceLinking;
    public RRC_MeasObjectId servingCellMO;
    public RRC_ServingCellConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tdd-UL-DL-ConfigurationDedicated","initialDownlinkBWP","downlinkBWP-ToReleaseList","downlinkBWP-ToAddModList","firstActiveDownlinkBWP-Id","bwp-InactivityTimer","defaultDownlinkBWP-Id","uplinkConfig","supplementaryUplink","pdcch-ServingCellConfig","pdsch-ServingCellConfig","csi-MeasConfig","sCellDeactivationTimer","crossCarrierSchedulingConfig","tag-Id","dummy","pathlossReferenceLinking","servingCellMO","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tdd_UL_DL_ConfigurationDedicated","initialDownlinkBWP","downlinkBWP_ToReleaseList","downlinkBWP_ToAddModList","firstActiveDownlinkBWP_Id","bwp_InactivityTimer","defaultDownlinkBWP_Id","uplinkConfig","supplementaryUplink","pdcch_ServingCellConfig","pdsch_ServingCellConfig","csi_MeasConfig","sCellDeactivationTimer","crossCarrierSchedulingConfig","tag_Id","dummy","pathlossReferenceLinking","servingCellMO","ext1" };
    }

    @Override
    public String getAsnName() {
        return "ServingCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "ServingCellConfig";
    }

}

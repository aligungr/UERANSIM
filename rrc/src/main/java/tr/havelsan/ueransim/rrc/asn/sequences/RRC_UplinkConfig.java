/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUSCH_ServingCellConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_SRS_CarrierSwitching;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkConfig__uplinkBWP_ToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkConfig__uplinkBWP_ToReleaseList;

public class RRC_UplinkConfig extends RRC_Sequence {

    public RRC_BWP_UplinkDedicated initialUplinkBWP;
    public RRC_UplinkConfig__uplinkBWP_ToReleaseList uplinkBWP_ToReleaseList;
    public RRC_UplinkConfig__uplinkBWP_ToAddModList uplinkBWP_ToAddModList;
    public RRC_BWP_Id firstActiveUplinkBWP_Id;
    public RRC_SetupRelease_PUSCH_ServingCellConfig pusch_ServingCellConfig;
    public RRC_SetupRelease_SRS_CarrierSwitching carrierSwitching;
    public RRC_UplinkConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "initialUplinkBWP","uplinkBWP-ToReleaseList","uplinkBWP-ToAddModList","firstActiveUplinkBWP-Id","pusch-ServingCellConfig","carrierSwitching","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "initialUplinkBWP","uplinkBWP_ToReleaseList","uplinkBWP_ToAddModList","firstActiveUplinkBWP_Id","pusch_ServingCellConfig","carrierSwitching","ext1" };
    }

    @Override
    public String getAsnName() {
        return "UplinkConfig";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkConfig";
    }

}

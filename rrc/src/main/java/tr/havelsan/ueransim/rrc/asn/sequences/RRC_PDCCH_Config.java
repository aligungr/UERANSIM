/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DownlinkPreemption;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUCCH_TPC_CommandConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUSCH_TPC_CommandConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_SRS_TPC_CommandConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDCCH_Config__controlResourceSetToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDCCH_Config__controlResourceSetToReleaseList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDCCH_Config__searchSpacesToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDCCH_Config__searchSpacesToReleaseList;

public class RRC_PDCCH_Config extends RRC_Sequence {

    public RRC_PDCCH_Config__controlResourceSetToAddModList controlResourceSetToAddModList;
    public RRC_PDCCH_Config__controlResourceSetToReleaseList controlResourceSetToReleaseList;
    public RRC_PDCCH_Config__searchSpacesToAddModList searchSpacesToAddModList;
    public RRC_PDCCH_Config__searchSpacesToReleaseList searchSpacesToReleaseList;
    public RRC_SetupRelease_DownlinkPreemption downlinkPreemption;
    public RRC_SetupRelease_PUSCH_TPC_CommandConfig tpc_PUSCH;
    public RRC_SetupRelease_PUCCH_TPC_CommandConfig tpc_PUCCH;
    public RRC_SetupRelease_SRS_TPC_CommandConfig tpc_SRS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "controlResourceSetToAddModList","controlResourceSetToReleaseList","searchSpacesToAddModList","searchSpacesToReleaseList","downlinkPreemption","tpc-PUSCH","tpc-PUCCH","tpc-SRS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "controlResourceSetToAddModList","controlResourceSetToReleaseList","searchSpacesToAddModList","searchSpacesToReleaseList","downlinkPreemption","tpc_PUSCH","tpc_PUCCH","tpc_SRS" };
    }

    @Override
    public String getAsnName() {
        return "PDCCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PDCCH-Config";
    }

}

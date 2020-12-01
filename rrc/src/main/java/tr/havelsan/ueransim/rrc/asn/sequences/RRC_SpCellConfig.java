/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RLF_TimersAndConstants;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SpCellConfig extends RRC_Sequence {

    public RRC_ServCellIndex servCellIndex;
    public RRC_ReconfigurationWithSync reconfigurationWithSync;
    public RRC_SetupRelease_RLF_TimersAndConstants rlf_TimersAndConstants;
    public RRC_Integer rlmInSyncOutOfSyncThreshold;
    public RRC_ServingCellConfig spCellConfigDedicated;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servCellIndex","reconfigurationWithSync","rlf-TimersAndConstants","rlmInSyncOutOfSyncThreshold","spCellConfigDedicated" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servCellIndex","reconfigurationWithSync","rlf_TimersAndConstants","rlmInSyncOutOfSyncThreshold","spCellConfigDedicated" };
    }

    @Override
    public String getAsnName() {
        return "SpCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SpCellConfig";
    }

}

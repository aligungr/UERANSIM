/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SpCellConfig extends AsnSequence {
    public RRC_ServCellIndex servCellIndex; // optional
    public RRC_ReconfigurationWithSync reconfigurationWithSync; // optional
    public RRC_SetupRelease_RLF_TimersAndConstants rlf_TimersAndConstants; // optional
    public RRC_rlmInSyncOutOfSyncThreshold rlmInSyncOutOfSyncThreshold; // optional
    public RRC_ServingCellConfig spCellConfigDedicated; // optional

    public static class RRC_SetupRelease_RLF_TimersAndConstants extends AsnChoice {
        public AsnNull release;
        public RRC_RLF_TimersAndConstants setup;
    }

    public static class RRC_rlmInSyncOutOfSyncThreshold extends AsnEnumerated {
        public static final RRC_rlmInSyncOutOfSyncThreshold N1 = new RRC_rlmInSyncOutOfSyncThreshold(0);
    
        private RRC_rlmInSyncOutOfSyncThreshold(long value) {
            super(value);
        }
    }
}


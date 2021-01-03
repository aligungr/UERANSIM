/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_BWP_DownlinkDedicated extends AsnSequence {
    public RRC_SetupRelease_PDCCH_Config pdcch_Config; // optional
    public RRC_SetupRelease_PDSCH_Config pdsch_Config; // optional
    public RRC_SetupRelease_SPS_Config sps_Config; // optional
    public RRC_SetupRelease_RadioLinkMonitoringConfig radioLinkMonitoringConfig; // optional

    public static class RRC_SetupRelease_PDCCH_Config extends AsnChoice {
        public AsnNull release;
        public RRC_PDCCH_Config setup;
    }

    public static class RRC_SetupRelease_SPS_Config extends AsnChoice {
        public AsnNull release;
        public RRC_SPS_Config setup;
    }

    public static class RRC_SetupRelease_RadioLinkMonitoringConfig extends AsnChoice {
        public AsnNull release;
        public RRC_RadioLinkMonitoringConfig setup;
    }

    public static class RRC_SetupRelease_PDSCH_Config extends AsnChoice {
        public AsnNull release;
        public RRC_PDSCH_Config setup;
    }
}


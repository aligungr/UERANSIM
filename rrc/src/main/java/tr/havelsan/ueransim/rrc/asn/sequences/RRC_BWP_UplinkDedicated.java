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

public class RRC_BWP_UplinkDedicated extends AsnSequence {
    public RRC_SetupRelease_PUCCH_Config pucch_Config; // optional
    public RRC_SetupRelease_PUSCH_Config pusch_Config; // optional
    public RRC_SetupRelease_ConfiguredGrantConfig configuredGrantConfig; // optional
    public RRC_SetupRelease_SRS_Config srs_Config; // optional
    public RRC_SetupRelease_BeamFailureRecoveryConfig beamFailureRecoveryConfig; // optional

    public static class RRC_SetupRelease_ConfiguredGrantConfig extends AsnChoice {
        public AsnNull release;
        public RRC_ConfiguredGrantConfig setup;
    }

    public static class RRC_SetupRelease_PUCCH_Config extends AsnChoice {
        public AsnNull release;
        public RRC_PUCCH_Config setup;
    }

    public static class RRC_SetupRelease_PUSCH_Config extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_Config setup;
    }

    public static class RRC_SetupRelease_SRS_Config extends AsnChoice {
        public AsnNull release;
        public RRC_SRS_Config setup;
    }

    public static class RRC_SetupRelease_BeamFailureRecoveryConfig extends AsnChoice {
        public AsnNull release;
        public RRC_BeamFailureRecoveryConfig setup;
    }
}


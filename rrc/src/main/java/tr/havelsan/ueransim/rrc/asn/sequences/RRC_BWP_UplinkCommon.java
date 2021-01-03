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

public class RRC_BWP_UplinkCommon extends AsnSequence {
    public RRC_BWP genericParameters; // mandatory
    public RRC_SetupRelease_RACH_ConfigCommon rach_ConfigCommon; // optional
    public RRC_SetupRelease_PUSCH_ConfigCommon pusch_ConfigCommon; // optional
    public RRC_SetupRelease_PUCCH_ConfigCommon pucch_ConfigCommon; // optional

    public static class RRC_SetupRelease_PUSCH_ConfigCommon extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_ConfigCommon setup;
    }

    public static class RRC_SetupRelease_RACH_ConfigCommon extends AsnChoice {
        public AsnNull release;
        public RRC_RACH_ConfigCommon setup;
    }

    public static class RRC_SetupRelease_PUCCH_ConfigCommon extends AsnChoice {
        public AsnNull release;
        public RRC_PUCCH_ConfigCommon setup;
    }
}


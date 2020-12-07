/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_BWP_DownlinkCommon extends AsnSequence {
    public RRC_BWP genericParameters; // mandatory
    public RRC_SetupRelease_PDCCH_ConfigCommon pdcch_ConfigCommon; // optional
    public RRC_SetupRelease_PDSCH_ConfigCommon pdsch_ConfigCommon; // optional

    public static class RRC_SetupRelease_PDCCH_ConfigCommon extends AsnChoice {
        public AsnNull release;
        public RRC_PDCCH_ConfigCommon setup;
    }

    public static class RRC_SetupRelease_PDSCH_ConfigCommon extends AsnChoice {
        public AsnNull release;
        public RRC_PDSCH_ConfigCommon setup;
    }
}


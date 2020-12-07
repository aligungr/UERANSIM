/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_OtherConfig_v1540 extends AsnSequence {
    public RRC_SetupRelease_OverheatingAssistanceConfig overheatingAssistanceConfig; // optional

    public static class RRC_SetupRelease_OverheatingAssistanceConfig extends AsnChoice {
        public AsnNull release;
        public RRC_OverheatingAssistanceConfig setup;
    }
}


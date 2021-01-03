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

public class RRC_ReferenceSignalConfig extends AsnSequence {
    public RRC_SSB_ConfigMobility ssb_ConfigMobility; // optional
    public RRC_SetupRelease_CSI_RS_ResourceConfigMobility csi_rs_ResourceConfigMobility; // optional

    public static class RRC_SetupRelease_CSI_RS_ResourceConfigMobility extends AsnChoice {
        public AsnNull release;
        public RRC_CSI_RS_ResourceConfigMobility setup;
    }
}


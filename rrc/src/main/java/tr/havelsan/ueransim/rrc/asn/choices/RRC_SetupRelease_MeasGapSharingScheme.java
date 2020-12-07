/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MeasGapSharingScheme;

public class RRC_SetupRelease_MeasGapSharingScheme extends AsnChoice {
    public AsnNull release;
    public RRC_MeasGapSharingScheme setup;
}


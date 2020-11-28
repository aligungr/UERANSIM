/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.inners;

import tr.havelsan.ueransim.rrc.core.RrcChoice;
import tr.havelsan.ueransim.rrc.core.RrcExtSequence;
import tr.havelsan.ueransim.rrc.sequences.RRC_RRCSetup_IEs;

public class RRC_RRCSetup_CriticalExtensions extends RrcChoice {
    public RRC_RRCSetup_IEs rrcSetup;
    public RrcExtSequence criticalExtensionsFuture;
}

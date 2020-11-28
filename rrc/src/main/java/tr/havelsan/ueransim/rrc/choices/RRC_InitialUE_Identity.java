/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.choices;

import tr.havelsan.ueransim.rrc.core.RrcChoice;
import tr.havelsan.ueransim.utils.bits.BitString;

public class RRC_InitialUE_Identity extends RrcChoice {
    public BitString ng_5G_S_TMSI_Part1; // 39-bit
    public BitString randomValue; // 39-bit
}

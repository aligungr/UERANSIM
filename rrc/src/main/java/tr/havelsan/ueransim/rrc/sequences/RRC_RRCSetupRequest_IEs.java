/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.sequences;

import tr.havelsan.ueransim.rrc.choices.RRC_InitialUE_Identity;
import tr.havelsan.ueransim.rrc.core.RrcSequence;
import tr.havelsan.ueransim.rrc.enums.RRC_EstablishmentCause;

public class RRC_RRCSetupRequest_IEs extends RrcSequence {
    public RRC_InitialUE_Identity ue_Identity;
    public RRC_EstablishmentCause establishmentCause;
}

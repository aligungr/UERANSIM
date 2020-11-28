/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.sequences;

import tr.havelsan.ueransim.rrc.core.RrcExtSequence;
import tr.havelsan.ueransim.rrc.core.RrcValue;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_RRCSetupComplete_IEs extends RrcExtSequence {
    public long selectedPLMN_Identity;
    public RRC_RegisteredAMF registeredAMF;
    public RrcValue guamiType; // TODO
    public RrcValue s_nssai_List; // TODO
    public RrcValue dedicatedNAS_Message; // TODO
    public RrcValue ng_5G_S_TMSI_Value; // TODO
    public OctetString lateNonCriticalExtension;
    public RrcExtSequence nonCriticalExtension;
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.sequences;

import tr.havelsan.ueransim.rrc.core.RrcExtSequence;
import tr.havelsan.ueransim.rrc.core.RrcSequence;
import tr.havelsan.ueransim.rrc.core.RrcValue;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_RRCSetup_IEs extends RrcSequence {
    public RrcValue radioBearerConfig; // TODO
    public OctetString masterCellGroup; // CONTAINING CellGroupConfig
    public OctetString lateNonCriticalExtension;
    public RrcExtSequence nonCriticalExtension;
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasTimingList;

public class RRC_MeasurementTimingConfiguration_IEs extends AsnSequence {
    public RRC_MeasTimingList measTiming; // optional
    public RRC_MeasurementTimingConfiguration_v1550_IEs nonCriticalExtension; // optional
}


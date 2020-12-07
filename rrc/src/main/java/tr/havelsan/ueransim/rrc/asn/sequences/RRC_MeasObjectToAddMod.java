/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;

public class RRC_MeasObjectToAddMod extends AsnSequence {
    public RRC_MeasObjectId measObjectId; // mandatory
    public RRC_measObject measObject; // mandatory

    public static class RRC_measObject extends AsnChoice {
        public RRC_MeasObjectNR measObjectNR;
        public RRC_MeasObjectEUTRA measObjectEUTRA;
    }
}


/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PRB_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_PUCCH_Resource extends AsnSequence {
    public RRC_PUCCH_ResourceId pucch_ResourceId; // mandatory
    public RRC_PRB_Id startingPRB; // mandatory
    public RRC_intraSlotFrequencyHopping intraSlotFrequencyHopping; // optional
    public RRC_PRB_Id secondHopPRB; // optional
    public RRC_format format; // mandatory

    public static class RRC_format extends AsnChoice {
        public RRC_PUCCH_format0 format0;
        public RRC_PUCCH_format1 format1;
        public RRC_PUCCH_format2 format2;
        public RRC_PUCCH_format3 format3;
        public RRC_PUCCH_format4 format4;
    }

    public static class RRC_intraSlotFrequencyHopping extends AsnEnumerated {
        public static final RRC_intraSlotFrequencyHopping ENABLED = new RRC_intraSlotFrequencyHopping(0);
    
        private RRC_intraSlotFrequencyHopping(long value) {
            super(value);
        }
    }
}


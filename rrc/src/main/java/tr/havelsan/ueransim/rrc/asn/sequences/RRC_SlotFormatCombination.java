/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SlotFormatCombinationId;

public class RRC_SlotFormatCombination extends AsnSequence {
    public RRC_SlotFormatCombinationId slotFormatCombinationId; // mandatory
    public RRC_slotFormats slotFormats; // mandatory, SIZE(1..256)

    // SIZE(1..256)
    public static class RRC_slotFormats extends AsnSequenceOf<AsnInteger> {
    }
}


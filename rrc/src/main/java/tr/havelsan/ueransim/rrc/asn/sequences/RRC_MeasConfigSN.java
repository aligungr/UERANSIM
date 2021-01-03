/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_MeasConfigSN extends AsnSequence {
    public RRC_measuredFrequenciesSN measuredFrequenciesSN; // optional, SIZE(1..32)

    // SIZE(1..32)
    public static class RRC_measuredFrequenciesSN extends AsnSequenceOf<RRC_NR_FreqInfo> {
    }
}


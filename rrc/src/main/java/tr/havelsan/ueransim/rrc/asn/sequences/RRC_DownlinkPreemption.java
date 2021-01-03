/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_DownlinkPreemption extends AsnSequence {
    public RRC_RNTI_Value int_RNTI; // mandatory
    public RRC_timeFrequencySet timeFrequencySet; // mandatory
    public AsnInteger dci_PayloadSize; // mandatory, VALUE(0..126)
    public RRC_int_ConfigurationPerServingCell int_ConfigurationPerServingCell; // mandatory, SIZE(1..32)

    public static class RRC_timeFrequencySet extends AsnEnumerated {
        public static final RRC_timeFrequencySet SET0 = new RRC_timeFrequencySet(0);
        public static final RRC_timeFrequencySet SET1 = new RRC_timeFrequencySet(1);
    
        private RRC_timeFrequencySet(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_int_ConfigurationPerServingCell extends AsnSequenceOf<RRC_INT_ConfigurationPerServingCell> {
    }
}


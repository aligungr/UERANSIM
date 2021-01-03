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
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_BWP extends AsnSequence {
    public AsnInteger locationAndBandwidth; // mandatory, VALUE(0..37949)
    public RRC_SubcarrierSpacing subcarrierSpacing; // mandatory
    public RRC_cyclicPrefix cyclicPrefix; // optional

    public static class RRC_cyclicPrefix extends AsnEnumerated {
        public static final RRC_cyclicPrefix EXTENDED = new RRC_cyclicPrefix(0);
    
        private RRC_cyclicPrefix(long value) {
            super(value);
        }
    }
}


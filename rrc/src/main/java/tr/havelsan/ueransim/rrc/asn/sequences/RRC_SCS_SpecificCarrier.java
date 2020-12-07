/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_SCS_SpecificCarrier extends AsnSequence {
    public AsnInteger offsetToCarrier; // mandatory, VALUE(0..2199)
    public RRC_SubcarrierSpacing subcarrierSpacing; // mandatory
    public AsnInteger carrierBandwidth; // mandatory, VALUE(1..275)
    public RRC_ext1_33 ext1; // optional

    public static class RRC_ext1_33 extends AsnSequence {
        public AsnInteger txDirectCurrentLocation_v1530; // optional, VALUE(0..4095)
    }
}


/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_AggregatedBandwidth;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_FreqBandInformationNR extends AsnSequence {
    public RRC_FreqBandIndicatorNR bandNR; // mandatory
    public RRC_AggregatedBandwidth maxBandwidthRequestedDL; // optional
    public RRC_AggregatedBandwidth maxBandwidthRequestedUL; // optional
    public AsnInteger maxCarriersRequestedDL; // optional, VALUE(1..32)
    public AsnInteger maxCarriersRequestedUL; // optional, VALUE(1..32)
}


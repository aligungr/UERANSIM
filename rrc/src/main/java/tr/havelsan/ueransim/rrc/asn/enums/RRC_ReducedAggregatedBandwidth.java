/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_ReducedAggregatedBandwidth extends AsnEnumerated {
    public static final RRC_ReducedAggregatedBandwidth MHZ0 = new RRC_ReducedAggregatedBandwidth(0);
    public static final RRC_ReducedAggregatedBandwidth MHZ10 = new RRC_ReducedAggregatedBandwidth(1);
    public static final RRC_ReducedAggregatedBandwidth MHZ20 = new RRC_ReducedAggregatedBandwidth(2);
    public static final RRC_ReducedAggregatedBandwidth MHZ30 = new RRC_ReducedAggregatedBandwidth(3);
    public static final RRC_ReducedAggregatedBandwidth MHZ40 = new RRC_ReducedAggregatedBandwidth(4);
    public static final RRC_ReducedAggregatedBandwidth MHZ50 = new RRC_ReducedAggregatedBandwidth(5);
    public static final RRC_ReducedAggregatedBandwidth MHZ60 = new RRC_ReducedAggregatedBandwidth(6);
    public static final RRC_ReducedAggregatedBandwidth MHZ80 = new RRC_ReducedAggregatedBandwidth(7);
    public static final RRC_ReducedAggregatedBandwidth MHZ100 = new RRC_ReducedAggregatedBandwidth(8);
    public static final RRC_ReducedAggregatedBandwidth MHZ200 = new RRC_ReducedAggregatedBandwidth(9);
    public static final RRC_ReducedAggregatedBandwidth MHZ300 = new RRC_ReducedAggregatedBandwidth(10);
    public static final RRC_ReducedAggregatedBandwidth MHZ400 = new RRC_ReducedAggregatedBandwidth(11);

    private RRC_ReducedAggregatedBandwidth(long value) {
        super(value);
    }
}


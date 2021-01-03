/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_AggregatedBandwidth extends AsnEnumerated {
    public static final RRC_AggregatedBandwidth MHZ50 = new RRC_AggregatedBandwidth(0);
    public static final RRC_AggregatedBandwidth MHZ100 = new RRC_AggregatedBandwidth(1);
    public static final RRC_AggregatedBandwidth MHZ150 = new RRC_AggregatedBandwidth(2);
    public static final RRC_AggregatedBandwidth MHZ200 = new RRC_AggregatedBandwidth(3);
    public static final RRC_AggregatedBandwidth MHZ250 = new RRC_AggregatedBandwidth(4);
    public static final RRC_AggregatedBandwidth MHZ300 = new RRC_AggregatedBandwidth(5);
    public static final RRC_AggregatedBandwidth MHZ350 = new RRC_AggregatedBandwidth(6);
    public static final RRC_AggregatedBandwidth MHZ400 = new RRC_AggregatedBandwidth(7);
    public static final RRC_AggregatedBandwidth MHZ450 = new RRC_AggregatedBandwidth(8);
    public static final RRC_AggregatedBandwidth MHZ500 = new RRC_AggregatedBandwidth(9);
    public static final RRC_AggregatedBandwidth MHZ550 = new RRC_AggregatedBandwidth(10);
    public static final RRC_AggregatedBandwidth MHZ600 = new RRC_AggregatedBandwidth(11);
    public static final RRC_AggregatedBandwidth MHZ650 = new RRC_AggregatedBandwidth(12);
    public static final RRC_AggregatedBandwidth MHZ700 = new RRC_AggregatedBandwidth(13);
    public static final RRC_AggregatedBandwidth MHZ750 = new RRC_AggregatedBandwidth(14);
    public static final RRC_AggregatedBandwidth MHZ800 = new RRC_AggregatedBandwidth(15);

    private RRC_AggregatedBandwidth(long value) {
        super(value);
    }
}


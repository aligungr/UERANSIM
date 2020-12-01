/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_ReducedAggregatedBandwidth extends RRC_Enumerated {

    public static final RRC_ReducedAggregatedBandwidth MHZ0 = new RRC_ReducedAggregatedBandwidth("mhz0");
    public static final RRC_ReducedAggregatedBandwidth MHZ10 = new RRC_ReducedAggregatedBandwidth("mhz10");
    public static final RRC_ReducedAggregatedBandwidth MHZ20 = new RRC_ReducedAggregatedBandwidth("mhz20");
    public static final RRC_ReducedAggregatedBandwidth MHZ30 = new RRC_ReducedAggregatedBandwidth("mhz30");
    public static final RRC_ReducedAggregatedBandwidth MHZ40 = new RRC_ReducedAggregatedBandwidth("mhz40");
    public static final RRC_ReducedAggregatedBandwidth MHZ50 = new RRC_ReducedAggregatedBandwidth("mhz50");
    public static final RRC_ReducedAggregatedBandwidth MHZ60 = new RRC_ReducedAggregatedBandwidth("mhz60");
    public static final RRC_ReducedAggregatedBandwidth MHZ80 = new RRC_ReducedAggregatedBandwidth("mhz80");
    public static final RRC_ReducedAggregatedBandwidth MHZ100 = new RRC_ReducedAggregatedBandwidth("mhz100");
    public static final RRC_ReducedAggregatedBandwidth MHZ200 = new RRC_ReducedAggregatedBandwidth("mhz200");
    public static final RRC_ReducedAggregatedBandwidth MHZ300 = new RRC_ReducedAggregatedBandwidth("mhz300");
    public static final RRC_ReducedAggregatedBandwidth MHZ400 = new RRC_ReducedAggregatedBandwidth("mhz400");

    protected RRC_ReducedAggregatedBandwidth(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ReducedAggregatedBandwidth";
    }

    @Override
    public String getXmlTagName() {
        return "ReducedAggregatedBandwidth";
    }

}

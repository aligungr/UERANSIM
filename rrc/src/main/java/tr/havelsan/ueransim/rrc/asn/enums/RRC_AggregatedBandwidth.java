/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_AggregatedBandwidth extends RRC_Enumerated {

    public static final RRC_AggregatedBandwidth MHZ50 = new RRC_AggregatedBandwidth("mhz50");
    public static final RRC_AggregatedBandwidth MHZ100 = new RRC_AggregatedBandwidth("mhz100");
    public static final RRC_AggregatedBandwidth MHZ150 = new RRC_AggregatedBandwidth("mhz150");
    public static final RRC_AggregatedBandwidth MHZ200 = new RRC_AggregatedBandwidth("mhz200");
    public static final RRC_AggregatedBandwidth MHZ250 = new RRC_AggregatedBandwidth("mhz250");
    public static final RRC_AggregatedBandwidth MHZ300 = new RRC_AggregatedBandwidth("mhz300");
    public static final RRC_AggregatedBandwidth MHZ350 = new RRC_AggregatedBandwidth("mhz350");
    public static final RRC_AggregatedBandwidth MHZ400 = new RRC_AggregatedBandwidth("mhz400");
    public static final RRC_AggregatedBandwidth MHZ450 = new RRC_AggregatedBandwidth("mhz450");
    public static final RRC_AggregatedBandwidth MHZ500 = new RRC_AggregatedBandwidth("mhz500");
    public static final RRC_AggregatedBandwidth MHZ550 = new RRC_AggregatedBandwidth("mhz550");
    public static final RRC_AggregatedBandwidth MHZ600 = new RRC_AggregatedBandwidth("mhz600");
    public static final RRC_AggregatedBandwidth MHZ650 = new RRC_AggregatedBandwidth("mhz650");
    public static final RRC_AggregatedBandwidth MHZ700 = new RRC_AggregatedBandwidth("mhz700");
    public static final RRC_AggregatedBandwidth MHZ750 = new RRC_AggregatedBandwidth("mhz750");
    public static final RRC_AggregatedBandwidth MHZ800 = new RRC_AggregatedBandwidth("mhz800");

    protected RRC_AggregatedBandwidth(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "AggregatedBandwidth";
    }

    @Override
    public String getXmlTagName() {
        return "AggregatedBandwidth";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_CA_BandwidthClassNR extends RRC_Enumerated {

    public static final RRC_CA_BandwidthClassNR A = new RRC_CA_BandwidthClassNR("a");
    public static final RRC_CA_BandwidthClassNR B = new RRC_CA_BandwidthClassNR("b");
    public static final RRC_CA_BandwidthClassNR C = new RRC_CA_BandwidthClassNR("c");
    public static final RRC_CA_BandwidthClassNR D = new RRC_CA_BandwidthClassNR("d");
    public static final RRC_CA_BandwidthClassNR E = new RRC_CA_BandwidthClassNR("e");
    public static final RRC_CA_BandwidthClassNR F = new RRC_CA_BandwidthClassNR("f");
    public static final RRC_CA_BandwidthClassNR G = new RRC_CA_BandwidthClassNR("g");
    public static final RRC_CA_BandwidthClassNR H = new RRC_CA_BandwidthClassNR("h");
    public static final RRC_CA_BandwidthClassNR I = new RRC_CA_BandwidthClassNR("i");
    public static final RRC_CA_BandwidthClassNR J = new RRC_CA_BandwidthClassNR("j");
    public static final RRC_CA_BandwidthClassNR K = new RRC_CA_BandwidthClassNR("k");
    public static final RRC_CA_BandwidthClassNR L = new RRC_CA_BandwidthClassNR("l");
    public static final RRC_CA_BandwidthClassNR M = new RRC_CA_BandwidthClassNR("m");
    public static final RRC_CA_BandwidthClassNR N = new RRC_CA_BandwidthClassNR("n");
    public static final RRC_CA_BandwidthClassNR O = new RRC_CA_BandwidthClassNR("o");
    public static final RRC_CA_BandwidthClassNR P = new RRC_CA_BandwidthClassNR("p");
    public static final RRC_CA_BandwidthClassNR Q = new RRC_CA_BandwidthClassNR("q");

    protected RRC_CA_BandwidthClassNR(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CA-BandwidthClassNR";
    }

    @Override
    public String getXmlTagName() {
        return "CA-BandwidthClassNR";
    }

}

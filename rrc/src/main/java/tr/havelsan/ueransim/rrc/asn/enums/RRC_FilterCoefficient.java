/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_FilterCoefficient extends RRC_Enumerated {

    public static final RRC_FilterCoefficient FC0 = new RRC_FilterCoefficient("fc0");
    public static final RRC_FilterCoefficient FC1 = new RRC_FilterCoefficient("fc1");
    public static final RRC_FilterCoefficient FC2 = new RRC_FilterCoefficient("fc2");
    public static final RRC_FilterCoefficient FC3 = new RRC_FilterCoefficient("fc3");
    public static final RRC_FilterCoefficient FC4 = new RRC_FilterCoefficient("fc4");
    public static final RRC_FilterCoefficient FC5 = new RRC_FilterCoefficient("fc5");
    public static final RRC_FilterCoefficient FC6 = new RRC_FilterCoefficient("fc6");
    public static final RRC_FilterCoefficient FC7 = new RRC_FilterCoefficient("fc7");
    public static final RRC_FilterCoefficient FC8 = new RRC_FilterCoefficient("fc8");
    public static final RRC_FilterCoefficient FC9 = new RRC_FilterCoefficient("fc9");
    public static final RRC_FilterCoefficient FC11 = new RRC_FilterCoefficient("fc11");
    public static final RRC_FilterCoefficient FC13 = new RRC_FilterCoefficient("fc13");
    public static final RRC_FilterCoefficient FC15 = new RRC_FilterCoefficient("fc15");
    public static final RRC_FilterCoefficient FC17 = new RRC_FilterCoefficient("fc17");
    public static final RRC_FilterCoefficient FC19 = new RRC_FilterCoefficient("fc19");
    public static final RRC_FilterCoefficient SPARE1 = new RRC_FilterCoefficient("spare1");

    protected RRC_FilterCoefficient(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "FilterCoefficient";
    }

    @Override
    public String getXmlTagName() {
        return "FilterCoefficient";
    }

}

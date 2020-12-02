/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_CA_BandwidthClassEUTRA extends RRC_Enumerated {

    public static final RRC_CA_BandwidthClassEUTRA A = new RRC_CA_BandwidthClassEUTRA("a");
    public static final RRC_CA_BandwidthClassEUTRA B = new RRC_CA_BandwidthClassEUTRA("b");
    public static final RRC_CA_BandwidthClassEUTRA C = new RRC_CA_BandwidthClassEUTRA("c");
    public static final RRC_CA_BandwidthClassEUTRA D = new RRC_CA_BandwidthClassEUTRA("d");
    public static final RRC_CA_BandwidthClassEUTRA E = new RRC_CA_BandwidthClassEUTRA("e");
    public static final RRC_CA_BandwidthClassEUTRA F = new RRC_CA_BandwidthClassEUTRA("f");

    protected RRC_CA_BandwidthClassEUTRA(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CA-BandwidthClassEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CA-BandwidthClassEUTRA";
    }

}

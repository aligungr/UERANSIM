/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_QuantityConfigRS extends RRC_Sequence {

    public RRC_FilterConfig ssb_FilterConfig;
    public RRC_FilterConfig csi_RS_FilterConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-FilterConfig","csi-RS-FilterConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_FilterConfig","csi_RS_FilterConfig" };
    }

    @Override
    public String getAsnName() {
        return "QuantityConfigRS";
    }

    @Override
    public String getXmlTagName() {
        return "QuantityConfigRS";
    }

}

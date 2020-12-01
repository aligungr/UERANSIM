/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_BWP extends RRC_Sequence {

    public RRC_Integer locationAndBandwidth;
    public RRC_SubcarrierSpacing subcarrierSpacing;
    public RRC_Integer cyclicPrefix;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "locationAndBandwidth","subcarrierSpacing","cyclicPrefix" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "locationAndBandwidth","subcarrierSpacing","cyclicPrefix" };
    }

    @Override
    public String getAsnName() {
        return "BWP";
    }

    @Override
    public String getXmlTagName() {
        return "BWP";
    }

}

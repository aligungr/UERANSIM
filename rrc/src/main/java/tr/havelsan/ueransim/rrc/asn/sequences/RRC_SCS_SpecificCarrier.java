/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_SCS_SpecificCarrier extends RRC_Sequence {

    public RRC_Integer offsetToCarrier;
    public RRC_SubcarrierSpacing subcarrierSpacing;
    public RRC_Integer carrierBandwidth;
    public RRC_SCS_SpecificCarrier__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "offsetToCarrier","subcarrierSpacing","carrierBandwidth","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "offsetToCarrier","subcarrierSpacing","carrierBandwidth","ext1" };
    }

    @Override
    public String getAsnName() {
        return "SCS-SpecificCarrier";
    }

    @Override
    public String getXmlTagName() {
        return "SCS-SpecificCarrier";
    }

}

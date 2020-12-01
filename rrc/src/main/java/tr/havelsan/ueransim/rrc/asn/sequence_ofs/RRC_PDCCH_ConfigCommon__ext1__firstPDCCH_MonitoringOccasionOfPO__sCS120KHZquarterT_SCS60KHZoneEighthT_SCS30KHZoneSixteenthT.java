/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_PDCCH_ConfigCommon__ext1__firstPDCCH_MonitoringOccasionOfPO__sCS120KHZquarterT_SCS60KHZoneEighthT_SCS30KHZoneSixteenthT extends RRC_SequenceOf<RRC_Integer> {

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

    @Override
    public Class<RRC_Integer> getItemType() {
        return RRC_Integer.class;
    }

}

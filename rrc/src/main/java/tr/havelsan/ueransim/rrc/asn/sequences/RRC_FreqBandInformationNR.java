/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_AggregatedBandwidth;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_FreqBandInformationNR extends RRC_Sequence {

    public RRC_FreqBandIndicatorNR bandNR;
    public RRC_AggregatedBandwidth maxBandwidthRequestedDL;
    public RRC_AggregatedBandwidth maxBandwidthRequestedUL;
    public RRC_Integer maxCarriersRequestedDL;
    public RRC_Integer maxCarriersRequestedUL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandNR","maxBandwidthRequestedDL","maxBandwidthRequestedUL","maxCarriersRequestedDL","maxCarriersRequestedUL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandNR","maxBandwidthRequestedDL","maxBandwidthRequestedUL","maxCarriersRequestedDL","maxCarriersRequestedUL" };
    }

    @Override
    public String getAsnName() {
        return "FreqBandInformationNR";
    }

    @Override
    public String getXmlTagName() {
        return "FreqBandInformationNR";
    }

}

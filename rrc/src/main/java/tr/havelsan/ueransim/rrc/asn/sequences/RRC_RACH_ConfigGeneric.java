/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RACH_ConfigGeneric extends RRC_Sequence {

    public RRC_Integer prach_ConfigurationIndex;
    public RRC_Integer msg1_FDM;
    public RRC_Integer msg1_FrequencyStart;
    public RRC_Integer zeroCorrelationZoneConfig;
    public RRC_Integer preambleReceivedTargetPower;
    public RRC_Integer preambleTransMax;
    public RRC_Integer powerRampingStep;
    public RRC_Integer ra_ResponseWindow;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "prach-ConfigurationIndex","msg1-FDM","msg1-FrequencyStart","zeroCorrelationZoneConfig","preambleReceivedTargetPower","preambleTransMax","powerRampingStep","ra-ResponseWindow" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "prach_ConfigurationIndex","msg1_FDM","msg1_FrequencyStart","zeroCorrelationZoneConfig","preambleReceivedTargetPower","preambleTransMax","powerRampingStep","ra_ResponseWindow" };
    }

    @Override
    public String getAsnName() {
        return "RACH-ConfigGeneric";
    }

    @Override
    public String getXmlTagName() {
        return "RACH-ConfigGeneric";
    }

}

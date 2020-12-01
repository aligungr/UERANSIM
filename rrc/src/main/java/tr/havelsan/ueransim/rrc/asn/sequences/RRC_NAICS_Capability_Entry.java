/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_NAICS_Capability_Entry extends RRC_Sequence {

    public RRC_Integer numberOfNAICS_CapableCC;
    public RRC_Integer numberOfAggregatedPRB;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "numberOfNAICS-CapableCC","numberOfAggregatedPRB" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "numberOfNAICS_CapableCC","numberOfAggregatedPRB" };
    }

    @Override
    public String getAsnName() {
        return "NAICS-Capability-Entry";
    }

    @Override
    public String getXmlTagName() {
        return "NAICS-Capability-Entry";
    }

}

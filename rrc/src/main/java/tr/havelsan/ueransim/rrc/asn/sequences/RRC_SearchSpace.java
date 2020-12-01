/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SearchSpace__monitoringSlotPeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SearchSpace__searchSpaceType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;

public class RRC_SearchSpace extends RRC_Sequence {

    public RRC_SearchSpaceId searchSpaceId;
    public RRC_ControlResourceSetId controlResourceSetId;
    public RRC_SearchSpace__monitoringSlotPeriodicityAndOffset monitoringSlotPeriodicityAndOffset;
    public RRC_Integer duration;
    public RRC_BitString monitoringSymbolsWithinSlot;
    public RRC_SearchSpace__nrofCandidates nrofCandidates;
    public RRC_SearchSpace__searchSpaceType searchSpaceType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "searchSpaceId","controlResourceSetId","monitoringSlotPeriodicityAndOffset","duration","monitoringSymbolsWithinSlot","nrofCandidates","searchSpaceType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "searchSpaceId","controlResourceSetId","monitoringSlotPeriodicityAndOffset","duration","monitoringSymbolsWithinSlot","nrofCandidates","searchSpaceType" };
    }

    @Override
    public String getAsnName() {
        return "SearchSpace";
    }

    @Override
    public String getXmlTagName() {
        return "SearchSpace";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_FrequencyOccupation extends RRC_Sequence {

    public RRC_Integer startingRB;
    public RRC_Integer nrofRBs;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "startingRB","nrofRBs" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "startingRB","nrofRBs" };
    }

    @Override
    public String getAsnName() {
        return "CSI-FrequencyOccupation";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-FrequencyOccupation";
    }

}

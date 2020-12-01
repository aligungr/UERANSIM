/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PUCCH_Resource__format;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PRB_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_PUCCH_Resource extends RRC_Sequence {

    public RRC_PUCCH_ResourceId pucch_ResourceId;
    public RRC_PRB_Id startingPRB;
    public RRC_Integer intraSlotFrequencyHopping;
    public RRC_PRB_Id secondHopPRB;
    public RRC_PUCCH_Resource__format format;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-ResourceId","startingPRB","intraSlotFrequencyHopping","secondHopPRB","format" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_ResourceId","startingPRB","intraSlotFrequencyHopping","secondHopPRB","format" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-Resource";
    }

}

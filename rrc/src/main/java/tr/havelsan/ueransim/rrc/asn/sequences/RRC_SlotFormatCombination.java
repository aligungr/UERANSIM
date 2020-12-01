/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SlotFormatCombinationId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SlotFormatCombination__slotFormats;

public class RRC_SlotFormatCombination extends RRC_Sequence {

    public RRC_SlotFormatCombinationId slotFormatCombinationId;
    public RRC_SlotFormatCombination__slotFormats slotFormats;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "slotFormatCombinationId","slotFormats" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "slotFormatCombinationId","slotFormats" };
    }

    @Override
    public String getAsnName() {
        return "SlotFormatCombination";
    }

    @Override
    public String getXmlTagName() {
        return "SlotFormatCombination";
    }

}

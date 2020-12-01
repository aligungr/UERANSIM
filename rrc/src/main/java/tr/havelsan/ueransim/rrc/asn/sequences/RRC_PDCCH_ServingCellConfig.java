/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_SlotFormatIndicator;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCCH_ServingCellConfig extends RRC_Sequence {

    public RRC_SetupRelease_SlotFormatIndicator slotFormatIndicator;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "slotFormatIndicator" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "slotFormatIndicator" };
    }

    @Override
    public String getAsnName() {
        return "PDCCH-ServingCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PDCCH-ServingCellConfig";
    }

}

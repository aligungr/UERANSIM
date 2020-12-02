/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ServingCellConfigCommonSIB__ssb_PositionsInBurst extends RRC_Sequence {

    public RRC_BitString inOneGroup;
    public RRC_BitString groupPresence;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "inOneGroup","groupPresence" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "inOneGroup","groupPresence" };
    }

}

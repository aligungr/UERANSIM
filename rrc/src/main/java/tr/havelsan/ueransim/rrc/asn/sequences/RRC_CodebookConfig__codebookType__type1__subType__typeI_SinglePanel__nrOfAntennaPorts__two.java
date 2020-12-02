/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__two extends RRC_Sequence {

    public RRC_BitString twoTX_CodebookSubsetRestriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "twoTX-CodebookSubsetRestriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "twoTX_CodebookSubsetRestriction" };
    }

}

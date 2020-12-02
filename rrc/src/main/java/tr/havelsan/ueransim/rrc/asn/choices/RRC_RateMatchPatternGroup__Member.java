/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;

public class RRC_RateMatchPatternGroup__Member extends RRC_Choice {

    public RRC_RateMatchPatternId cellLevel;
    public RRC_RateMatchPatternId bwpLevel;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellLevel","bwpLevel" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellLevel","bwpLevel" };
    }

}
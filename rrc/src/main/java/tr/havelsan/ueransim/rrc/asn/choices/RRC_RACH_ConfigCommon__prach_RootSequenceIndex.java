/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_RACH_ConfigCommon__prach_RootSequenceIndex extends RRC_Choice {

    public RRC_Integer l839;
    public RRC_Integer l139;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "l839","l139" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "l839","l139" };
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CFRA__resources__csirs;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CFRA__resources__ssb;

public class RRC_CFRA__resources extends RRC_Choice {

    public RRC_CFRA__resources__ssb ssb;
    public RRC_CFRA__resources__csirs csirs;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb","csirs" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb","csirs" };
    }

}

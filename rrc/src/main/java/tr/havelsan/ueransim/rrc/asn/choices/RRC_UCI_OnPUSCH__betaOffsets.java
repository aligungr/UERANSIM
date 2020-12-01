/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UCI_OnPUSCH__betaOffsets__dynamic;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BetaOffsets;

public class RRC_UCI_OnPUSCH__betaOffsets extends RRC_Choice {

    public RRC_UCI_OnPUSCH__betaOffsets__dynamic dynamic;
    public RRC_BetaOffsets semiStatic;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dynamic","semiStatic" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dynamic","semiStatic" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasResultNR__measResult extends RRC_Sequence {

    public RRC_MeasResultNR__measResult__cellResults cellResults;
    public RRC_MeasResultNR__measResult__rsIndexResults rsIndexResults;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellResults","rsIndexResults" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellResults","rsIndexResults" };
    }

}

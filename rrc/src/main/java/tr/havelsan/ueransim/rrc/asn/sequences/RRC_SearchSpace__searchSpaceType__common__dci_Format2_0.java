/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SearchSpace__searchSpaceType__common__dci_Format2_0 extends RRC_Sequence {

    public RRC_SearchSpace__searchSpaceType__common__dci_Format2_0__nrofCandidates_SFI nrofCandidates_SFI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nrofCandidates-SFI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nrofCandidates_SFI" };
    }

}

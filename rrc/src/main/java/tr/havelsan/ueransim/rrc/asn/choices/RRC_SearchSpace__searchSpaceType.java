/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SearchSpace__searchSpaceType__common;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SearchSpace__searchSpaceType__ue_Specific;

public class RRC_SearchSpace__searchSpaceType extends RRC_Choice {

    public RRC_SearchSpace__searchSpaceType__common common;
    public RRC_SearchSpace__searchSpaceType__ue_Specific ue_Specific;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "common","ue-Specific" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "common","ue_Specific" };
    }

}

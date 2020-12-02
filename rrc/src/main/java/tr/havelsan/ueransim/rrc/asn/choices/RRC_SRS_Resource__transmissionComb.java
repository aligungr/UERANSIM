/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource__transmissionComb__n2;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource__transmissionComb__n4;

public class RRC_SRS_Resource__transmissionComb extends RRC_Choice {

    public RRC_SRS_Resource__transmissionComb__n2 n2;
    public RRC_SRS_Resource__transmissionComb__n4 n4;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "n2","n4" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "n2","n4" };
    }

}

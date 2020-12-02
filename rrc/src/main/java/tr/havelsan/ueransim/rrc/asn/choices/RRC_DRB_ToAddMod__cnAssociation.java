/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SDAP_Config;

public class RRC_DRB_ToAddMod__cnAssociation extends RRC_Choice {

    public RRC_Integer eps_BearerIdentity;
    public RRC_SDAP_Config sdap_Config;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eps-BearerIdentity","sdap-Config" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eps_BearerIdentity","sdap_Config" };
    }

}

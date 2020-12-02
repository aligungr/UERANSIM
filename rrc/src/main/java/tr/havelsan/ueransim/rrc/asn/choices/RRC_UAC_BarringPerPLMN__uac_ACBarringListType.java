/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerCatList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerPLMN__uac_ACBarringListType__uac_ImplicitACBarringList;

public class RRC_UAC_BarringPerPLMN__uac_ACBarringListType extends RRC_Choice {

    public RRC_UAC_BarringPerPLMN__uac_ACBarringListType__uac_ImplicitACBarringList uac_ImplicitACBarringList;
    public RRC_UAC_BarringPerCatList uac_ExplicitACBarringList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uac-ImplicitACBarringList","uac-ExplicitACBarringList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uac_ImplicitACBarringList","uac_ExplicitACBarringList" };
    }

}

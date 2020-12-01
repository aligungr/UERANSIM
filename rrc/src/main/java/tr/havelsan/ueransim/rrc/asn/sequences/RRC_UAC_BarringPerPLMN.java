/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_UAC_BarringPerPLMN__uac_ACBarringListType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UAC_BarringPerPLMN extends RRC_Sequence {

    public RRC_Integer plmn_IdentityIndex;
    public RRC_UAC_BarringPerPLMN__uac_ACBarringListType uac_ACBarringListType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityIndex","uac-ACBarringListType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityIndex","uac_ACBarringListType" };
    }

    @Override
    public String getAsnName() {
        return "UAC-BarringPerPLMN";
    }

    @Override
    public String getXmlTagName() {
        return "UAC-BarringPerPLMN";
    }

}

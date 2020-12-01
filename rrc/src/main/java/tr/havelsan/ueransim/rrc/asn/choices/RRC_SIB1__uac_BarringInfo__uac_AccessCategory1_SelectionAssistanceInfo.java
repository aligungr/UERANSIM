/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_UAC_AccessCategory1_SelectionAssistanceInfo;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo__individualPLMNList;

public class RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo extends RRC_Choice {

    public RRC_UAC_AccessCategory1_SelectionAssistanceInfo plmnCommon;
    public RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo__individualPLMNList individualPLMNList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmnCommon","individualPLMNList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmnCommon","individualPLMNList" };
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

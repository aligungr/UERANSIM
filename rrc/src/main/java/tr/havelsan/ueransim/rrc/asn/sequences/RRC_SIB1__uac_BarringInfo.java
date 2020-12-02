/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringInfoSetList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerCatList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerPLMN_List;

public class RRC_SIB1__uac_BarringInfo extends RRC_Sequence {

    public RRC_UAC_BarringPerCatList uac_BarringForCommon;
    public RRC_UAC_BarringPerPLMN_List uac_BarringPerPLMN_List;
    public RRC_UAC_BarringInfoSetList uac_BarringInfoSetList;
    public RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo uac_AccessCategory1_SelectionAssistanceInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uac-BarringForCommon","uac-BarringPerPLMN-List","uac-BarringInfoSetList","uac-AccessCategory1-SelectionAssistanceInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uac_BarringForCommon","uac_BarringPerPLMN_List","uac_BarringInfoSetList","uac_AccessCategory1_SelectionAssistanceInfo" };
    }

}

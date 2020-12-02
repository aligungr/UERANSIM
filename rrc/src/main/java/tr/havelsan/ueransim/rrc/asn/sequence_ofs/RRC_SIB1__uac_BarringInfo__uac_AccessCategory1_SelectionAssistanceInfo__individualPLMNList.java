/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_UAC_AccessCategory1_SelectionAssistanceInfo;

public class RRC_SIB1__uac_BarringInfo__uac_AccessCategory1_SelectionAssistanceInfo__individualPLMNList extends RRC_SequenceOf<RRC_UAC_AccessCategory1_SelectionAssistanceInfo> {

    @Override
    public Class<RRC_UAC_AccessCategory1_SelectionAssistanceInfo> getItemType() {
        return RRC_UAC_AccessCategory1_SelectionAssistanceInfo.class;
    }

}

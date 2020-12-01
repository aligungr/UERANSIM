/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UAC_BarringPerPLMN;

public class RRC_UAC_BarringPerPLMN_List extends RRC_SequenceOf<RRC_UAC_BarringPerPLMN> {

    @Override
    public String getAsnName() {
        return "UAC-BarringPerPLMN-List";
    }

    @Override
    public String getXmlTagName() {
        return "UAC-BarringPerPLMN-List";
    }

    @Override
    public Class<RRC_UAC_BarringPerPLMN> getItemType() {
        return RRC_UAC_BarringPerPLMN.class;
    }

}

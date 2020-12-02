/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_UAC_BarringInfoSetIndex;

public class RRC_UAC_BarringPerPLMN__uac_ACBarringListType__uac_ImplicitACBarringList extends RRC_SequenceOf<RRC_UAC_BarringInfoSetIndex> {

    @Override
    public Class<RRC_UAC_BarringInfoSetIndex> getItemType() {
        return RRC_UAC_BarringInfoSetIndex.class;
    }

}

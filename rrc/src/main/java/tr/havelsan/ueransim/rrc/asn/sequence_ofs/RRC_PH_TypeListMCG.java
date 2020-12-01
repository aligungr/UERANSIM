/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PH_InfoMCG;

public class RRC_PH_TypeListMCG extends RRC_SequenceOf<RRC_PH_InfoMCG> {

    @Override
    public String getAsnName() {
        return "PH-TypeListMCG";
    }

    @Override
    public String getXmlTagName() {
        return "PH-TypeListMCG";
    }

    @Override
    public Class<RRC_PH_InfoMCG> getItemType() {
        return RRC_PH_InfoMCG.class;
    }

}

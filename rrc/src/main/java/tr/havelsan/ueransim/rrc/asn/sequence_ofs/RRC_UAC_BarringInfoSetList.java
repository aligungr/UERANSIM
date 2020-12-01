/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UAC_BarringInfoSet;

public class RRC_UAC_BarringInfoSetList extends RRC_SequenceOf<RRC_UAC_BarringInfoSet> {

    @Override
    public String getAsnName() {
        return "UAC-BarringInfoSetList";
    }

    @Override
    public String getXmlTagName() {
        return "UAC-BarringInfoSetList";
    }

    @Override
    public Class<RRC_UAC_BarringInfoSet> getItemType() {
        return RRC_UAC_BarringInfoSet.class;
    }

}

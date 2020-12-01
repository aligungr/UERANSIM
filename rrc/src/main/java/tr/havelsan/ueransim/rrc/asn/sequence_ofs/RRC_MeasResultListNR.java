/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResultNR;

public class RRC_MeasResultListNR extends RRC_SequenceOf<RRC_MeasResultNR> {

    @Override
    public String getAsnName() {
        return "MeasResultListNR";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultListNR";
    }

    @Override
    public Class<RRC_MeasResultNR> getItemType() {
        return RRC_MeasResultNR.class;
    }

}

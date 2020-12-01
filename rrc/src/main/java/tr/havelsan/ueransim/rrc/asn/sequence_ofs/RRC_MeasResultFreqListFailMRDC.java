/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResult2EUTRA;

public class RRC_MeasResultFreqListFailMRDC extends RRC_SequenceOf<RRC_MeasResult2EUTRA> {

    @Override
    public String getAsnName() {
        return "MeasResultFreqListFailMRDC";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultFreqListFailMRDC";
    }

    @Override
    public Class<RRC_MeasResult2EUTRA> getItemType() {
        return RRC_MeasResult2EUTRA.class;
    }

}

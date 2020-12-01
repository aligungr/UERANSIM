/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_CandidateServingFreqListEUTRA extends RRC_SequenceOf<RRC_ARFCN_ValueEUTRA> {

    @Override
    public String getAsnName() {
        return "CandidateServingFreqListEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CandidateServingFreqListEUTRA";
    }

    @Override
    public Class<RRC_ARFCN_ValueEUTRA> getItemType() {
        return RRC_ARFCN_ValueEUTRA.class;
    }

}

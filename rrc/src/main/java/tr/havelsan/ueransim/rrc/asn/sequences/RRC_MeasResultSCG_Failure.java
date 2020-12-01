/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultList2NR;

public class RRC_MeasResultSCG_Failure extends RRC_Sequence {

    public RRC_MeasResultList2NR measResultPerMOList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measResultPerMOList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measResultPerMOList" };
    }

    @Override
    public String getAsnName() {
        return "MeasResultSCG-Failure";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultSCG-Failure";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CA_ParametersNR_v1560 extends RRC_Sequence {

    public RRC_Integer diffNumerologyWithinPUCCH_GroupLargerSCS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "diffNumerologyWithinPUCCH-GroupLargerSCS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "diffNumerologyWithinPUCCH_GroupLargerSCS" };
    }

    @Override
    public String getAsnName() {
        return "CA-ParametersNR-v1560";
    }

    @Override
    public String getXmlTagName() {
        return "CA-ParametersNR-v1560";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ProcessingParameters extends RRC_Sequence {

    public RRC_Integer fallback;
    public RRC_ProcessingParameters__differentTB_PerSlot differentTB_PerSlot;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "fallback","differentTB-PerSlot" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "fallback","differentTB_PerSlot" };
    }

    @Override
    public String getAsnName() {
        return "ProcessingParameters";
    }

    @Override
    public String getXmlTagName() {
        return "ProcessingParameters";
    }

}

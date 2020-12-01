/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_IMS_ParametersFRX_Diff extends RRC_Sequence {

    public RRC_Integer voiceOverNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "voiceOverNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "voiceOverNR" };
    }

    @Override
    public String getAsnName() {
        return "IMS-ParametersFRX-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "IMS-ParametersFRX-Diff";
    }

}

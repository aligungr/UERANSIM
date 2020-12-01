/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_IMS_ParametersCommon extends RRC_Sequence {

    public RRC_Integer voiceOverEUTRA_5GC;
    public RRC_IMS_ParametersCommon__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "voiceOverEUTRA-5GC","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "voiceOverEUTRA_5GC","ext1" };
    }

    @Override
    public String getAsnName() {
        return "IMS-ParametersCommon";
    }

    @Override
    public String getXmlTagName() {
        return "IMS-ParametersCommon";
    }

}

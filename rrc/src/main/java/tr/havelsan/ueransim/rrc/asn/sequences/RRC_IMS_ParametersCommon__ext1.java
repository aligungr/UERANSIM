/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_IMS_ParametersCommon__ext1 extends RRC_Sequence {

    public RRC_Integer voiceOverSCG_BearerEUTRA_5GC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "voiceOverSCG-BearerEUTRA-5GC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "voiceOverSCG_BearerEUTRA_5GC" };
    }

}

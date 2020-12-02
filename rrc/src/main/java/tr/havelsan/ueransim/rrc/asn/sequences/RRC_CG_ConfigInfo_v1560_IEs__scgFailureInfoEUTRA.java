/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CG_ConfigInfo_v1560_IEs__scgFailureInfoEUTRA extends RRC_Sequence {

    public RRC_Integer failureTypeEUTRA;
    public RRC_OctetString measResultSCG_EUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "failureTypeEUTRA","measResultSCG-EUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "failureTypeEUTRA","measResultSCG_EUTRA" };
    }

}

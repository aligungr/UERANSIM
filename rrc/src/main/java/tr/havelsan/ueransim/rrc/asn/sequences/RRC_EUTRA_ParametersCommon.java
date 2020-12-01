/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_EUTRA_ParametersCommon extends RRC_Sequence {

    public RRC_Integer mfbi_EUTRA;
    public RRC_BitString modifiedMPR_BehaviorEUTRA;
    public RRC_Integer multiNS_Pmax_EUTRA;
    public RRC_Integer rs_SINR_MeasEUTRA;
    public RRC_EUTRA_ParametersCommon__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mfbi-EUTRA","modifiedMPR-BehaviorEUTRA","multiNS-Pmax-EUTRA","rs-SINR-MeasEUTRA","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mfbi_EUTRA","modifiedMPR_BehaviorEUTRA","multiNS_Pmax_EUTRA","rs_SINR_MeasEUTRA","ext1" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-ParametersCommon";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-ParametersCommon";
    }

}

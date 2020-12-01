/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_ConfigRestrictModReqSCG__ext1 extends RRC_Sequence {

    public RRC_Integer requestedPDCCH_BlindDetectionSCG;
    public RRC_P_Max requestedP_MaxEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "requestedPDCCH-BlindDetectionSCG","requestedP-MaxEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "requestedPDCCH_BlindDetectionSCG","requestedP_MaxEUTRA" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}

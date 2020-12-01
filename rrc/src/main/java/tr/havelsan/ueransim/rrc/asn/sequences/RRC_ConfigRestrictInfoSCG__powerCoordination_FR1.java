/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_ConfigRestrictInfoSCG__powerCoordination_FR1 extends RRC_Sequence {

    public RRC_P_Max p_maxNR_FR1;
    public RRC_P_Max p_maxEUTRA;
    public RRC_P_Max p_maxUE_FR1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "p-maxNR-FR1","p-maxEUTRA","p-maxUE-FR1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "p_maxNR_FR1","p_maxEUTRA","p_maxUE_FR1" };
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

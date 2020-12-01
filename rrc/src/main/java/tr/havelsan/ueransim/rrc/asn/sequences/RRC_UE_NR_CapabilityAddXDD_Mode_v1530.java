/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_CapabilityAddXDD_Mode_v1530 extends RRC_Sequence {

    public RRC_EUTRA_ParametersXDD_Diff eutra_ParametersXDD_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra-ParametersXDD-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra_ParametersXDD_Diff" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-CapabilityAddXDD-Mode-v1530";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-CapabilityAddXDD-Mode-v1530";
    }

}

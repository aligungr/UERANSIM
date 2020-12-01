/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_CapabilityAddFRX_Mode_v1540 extends RRC_Sequence {

    public RRC_IMS_ParametersFRX_Diff ims_ParametersFRX_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ims-ParametersFRX-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ims_ParametersFRX_Diff" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-CapabilityAddFRX-Mode-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-CapabilityAddFRX-Mode-v1540";
    }

}

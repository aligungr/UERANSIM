/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_AdditionalSpectrumEmission;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_NR_NS_PmaxValue extends RRC_Sequence {

    public RRC_P_Max additionalPmax;
    public RRC_AdditionalSpectrumEmission additionalSpectrumEmission;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "additionalPmax","additionalSpectrumEmission" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "additionalPmax","additionalSpectrumEmission" };
    }

    @Override
    public String getAsnName() {
        return "NR-NS-PmaxValue";
    }

    @Override
    public String getXmlTagName() {
        return "NR-NS-PmaxValue";
    }

}

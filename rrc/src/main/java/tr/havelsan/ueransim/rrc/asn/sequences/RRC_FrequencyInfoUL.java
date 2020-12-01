/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_AdditionalSpectrumEmission;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FrequencyInfoUL__scs_SpecificCarrierList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR;

public class RRC_FrequencyInfoUL extends RRC_Sequence {

    public RRC_MultiFrequencyBandListNR frequencyBandList;
    public RRC_ARFCN_ValueNR absoluteFrequencyPointA;
    public RRC_FrequencyInfoUL__scs_SpecificCarrierList scs_SpecificCarrierList;
    public RRC_AdditionalSpectrumEmission additionalSpectrumEmission;
    public RRC_P_Max p_Max;
    public RRC_Integer frequencyShift7p5khz;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyBandList","absoluteFrequencyPointA","scs-SpecificCarrierList","additionalSpectrumEmission","p-Max","frequencyShift7p5khz" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyBandList","absoluteFrequencyPointA","scs_SpecificCarrierList","additionalSpectrumEmission","p_Max","frequencyShift7p5khz" };
    }

    @Override
    public String getAsnName() {
        return "FrequencyInfoUL";
    }

    @Override
    public String getXmlTagName() {
        return "FrequencyInfoUL";
    }

}

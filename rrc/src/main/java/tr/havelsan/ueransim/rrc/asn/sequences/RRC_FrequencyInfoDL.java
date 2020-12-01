/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FrequencyInfoDL__scs_SpecificCarrierList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR;

public class RRC_FrequencyInfoDL extends RRC_Sequence {

    public RRC_ARFCN_ValueNR absoluteFrequencySSB;
    public RRC_MultiFrequencyBandListNR frequencyBandList;
    public RRC_ARFCN_ValueNR absoluteFrequencyPointA;
    public RRC_FrequencyInfoDL__scs_SpecificCarrierList scs_SpecificCarrierList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "absoluteFrequencySSB","frequencyBandList","absoluteFrequencyPointA","scs-SpecificCarrierList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "absoluteFrequencySSB","frequencyBandList","absoluteFrequencyPointA","scs_SpecificCarrierList" };
    }

    @Override
    public String getAsnName() {
        return "FrequencyInfoDL";
    }

    @Override
    public String getXmlTagName() {
        return "FrequencyInfoDL";
    }

}

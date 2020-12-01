/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FrequencyInfoDL_SIB__scs_SpecificCarrierList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_FrequencyInfoDL_SIB extends RRC_Sequence {

    public RRC_MultiFrequencyBandListNR_SIB frequencyBandList;
    public RRC_Integer offsetToPointA;
    public RRC_FrequencyInfoDL_SIB__scs_SpecificCarrierList scs_SpecificCarrierList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyBandList","offsetToPointA","scs-SpecificCarrierList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyBandList","offsetToPointA","scs_SpecificCarrierList" };
    }

    @Override
    public String getAsnName() {
        return "FrequencyInfoDL-SIB";
    }

    @Override
    public String getXmlTagName() {
        return "FrequencyInfoDL-SIB";
    }

}

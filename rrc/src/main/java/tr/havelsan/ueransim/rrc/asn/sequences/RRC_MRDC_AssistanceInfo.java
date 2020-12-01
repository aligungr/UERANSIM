/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MRDC_AssistanceInfo__affectedCarrierFreqCombInfoListMRDC;

public class RRC_MRDC_AssistanceInfo extends RRC_Sequence {

    public RRC_MRDC_AssistanceInfo__affectedCarrierFreqCombInfoListMRDC affectedCarrierFreqCombInfoListMRDC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "affectedCarrierFreqCombInfoListMRDC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "affectedCarrierFreqCombInfoListMRDC" };
    }

    @Override
    public String getAsnName() {
        return "MRDC-AssistanceInfo";
    }

    @Override
    public String getXmlTagName() {
        return "MRDC-AssistanceInfo";
    }

}

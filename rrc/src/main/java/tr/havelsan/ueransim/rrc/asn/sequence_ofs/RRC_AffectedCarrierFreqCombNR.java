/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;

public class RRC_AffectedCarrierFreqCombNR extends RRC_SequenceOf<RRC_ARFCN_ValueNR> {

    @Override
    public String getAsnName() {
        return "AffectedCarrierFreqCombNR";
    }

    @Override
    public String getXmlTagName() {
        return "AffectedCarrierFreqCombNR";
    }

    @Override
    public Class<RRC_ARFCN_ValueNR> getItemType() {
        return RRC_ARFCN_ValueNR.class;
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_AffectedCarrierFreqCombInfoMRDC;

public class RRC_MRDC_AssistanceInfo__affectedCarrierFreqCombInfoListMRDC extends RRC_SequenceOf<RRC_AffectedCarrierFreqCombInfoMRDC> {

    @Override
    public Class<RRC_AffectedCarrierFreqCombInfoMRDC> getItemType() {
        return RRC_AffectedCarrierFreqCombInfoMRDC.class;
    }

}

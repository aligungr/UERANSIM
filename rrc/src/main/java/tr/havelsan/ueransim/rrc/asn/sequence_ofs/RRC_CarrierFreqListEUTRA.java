/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CarrierFreqEUTRA;

public class RRC_CarrierFreqListEUTRA extends RRC_SequenceOf<RRC_CarrierFreqEUTRA> {

    @Override
    public String getAsnName() {
        return "CarrierFreqListEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CarrierFreqListEUTRA";
    }

    @Override
    public Class<RRC_CarrierFreqEUTRA> getItemType() {
        return RRC_CarrierFreqEUTRA.class;
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_NR_FreqInfo;

public class RRC_MeasConfigSN__measuredFrequenciesSN extends RRC_SequenceOf<RRC_NR_FreqInfo> {

    @Override
    public Class<RRC_NR_FreqInfo> getItemType() {
        return RRC_NR_FreqInfo.class;
    }

}

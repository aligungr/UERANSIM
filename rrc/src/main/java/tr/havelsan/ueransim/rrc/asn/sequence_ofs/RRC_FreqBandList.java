/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_FreqBandInformation;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_FreqBandList extends RRC_SequenceOf<RRC_FreqBandInformation> {

    @Override
    public String getAsnName() {
        return "FreqBandList";
    }

    @Override
    public String getXmlTagName() {
        return "FreqBandList";
    }

    @Override
    public Class<RRC_FreqBandInformation> getItemType() {
        return RRC_FreqBandInformation.class;
    }

}

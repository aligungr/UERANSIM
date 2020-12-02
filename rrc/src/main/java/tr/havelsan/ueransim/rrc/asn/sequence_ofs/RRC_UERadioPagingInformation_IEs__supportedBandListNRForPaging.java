/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_UERadioPagingInformation_IEs__supportedBandListNRForPaging extends RRC_SequenceOf<RRC_FreqBandIndicatorNR> {

    @Override
    public Class<RRC_FreqBandIndicatorNR> getItemType() {
        return RRC_FreqBandIndicatorNR.class;
    }

}

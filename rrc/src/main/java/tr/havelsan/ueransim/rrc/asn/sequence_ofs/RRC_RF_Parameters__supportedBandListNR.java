/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandNR;

public class RRC_RF_Parameters__supportedBandListNR extends RRC_SequenceOf<RRC_BandNR> {

    @Override
    public Class<RRC_BandNR> getItemType() {
        return RRC_BandNR.class;
    }

}

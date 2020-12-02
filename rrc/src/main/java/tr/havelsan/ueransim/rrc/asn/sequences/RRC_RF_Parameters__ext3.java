/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationList_v1560;

public class RRC_RF_Parameters__ext3 extends RRC_Sequence {

    public RRC_BandCombinationList_v1560 supportedBandCombinationList_v1560;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedBandCombinationList-v1560" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedBandCombinationList_v1560" };
    }

}

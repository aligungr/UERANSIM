/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationList_v1550;

public class RRC_RF_Parameters__ext2 extends RRC_Sequence {

    public RRC_BandCombinationList_v1550 supportedBandCombinationList_v1550;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedBandCombinationList-v1550" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedBandCombinationList_v1550" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}

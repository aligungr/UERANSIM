/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationList_v1540;

public class RRC_RF_ParametersMRDC__ext1 extends RRC_Sequence {

    public RRC_Integer srs_SwitchingTimeRequested;
    public RRC_BandCombinationList_v1540 supportedBandCombinationList_v1540;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-SwitchingTimeRequested","supportedBandCombinationList-v1540" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_SwitchingTimeRequested","supportedBandCombinationList_v1540" };
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

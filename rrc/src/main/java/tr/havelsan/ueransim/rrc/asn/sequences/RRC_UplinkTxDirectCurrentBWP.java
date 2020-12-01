/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_UplinkTxDirectCurrentBWP extends RRC_Sequence {

    public RRC_BWP_Id bwp_Id;
    public RRC_Boolean shift7dot5kHz;
    public RRC_Integer txDirectCurrentLocation;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bwp-Id","shift7dot5kHz","txDirectCurrentLocation" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bwp_Id","shift7dot5kHz","txDirectCurrentLocation" };
    }

    @Override
    public String getAsnName() {
        return "UplinkTxDirectCurrentBWP";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkTxDirectCurrentBWP";
    }

}

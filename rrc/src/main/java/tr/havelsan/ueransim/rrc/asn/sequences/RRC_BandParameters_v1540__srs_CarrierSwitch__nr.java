/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandParameters_v1540__srs_CarrierSwitch__nr__srs_SwitchingTimesListNR;

public class RRC_BandParameters_v1540__srs_CarrierSwitch__nr extends RRC_Sequence {

    public RRC_BandParameters_v1540__srs_CarrierSwitch__nr__srs_SwitchingTimesListNR srs_SwitchingTimesListNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-SwitchingTimesListNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_SwitchingTimesListNR" };
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

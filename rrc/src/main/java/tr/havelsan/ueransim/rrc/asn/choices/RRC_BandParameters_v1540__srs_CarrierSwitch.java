/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandParameters_v1540__srs_CarrierSwitch__eutra;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandParameters_v1540__srs_CarrierSwitch__nr;

public class RRC_BandParameters_v1540__srs_CarrierSwitch extends RRC_Choice {

    public RRC_BandParameters_v1540__srs_CarrierSwitch__nr nr;
    public RRC_BandParameters_v1540__srs_CarrierSwitch__eutra eutra;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nr","eutra" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nr","eutra" };
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

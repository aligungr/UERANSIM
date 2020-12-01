/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandParameters_v1540__srs_TxSwitch extends RRC_Sequence {

    public RRC_Integer supportedSRS_TxPortSwitch;
    public RRC_Integer txSwitchImpactToRx;
    public RRC_Integer txSwitchWithAnotherBand;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedSRS-TxPortSwitch","txSwitchImpactToRx","txSwitchWithAnotherBand" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedSRS_TxPortSwitch","txSwitchImpactToRx","txSwitchWithAnotherBand" };
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

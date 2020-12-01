/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DummyI extends RRC_Sequence {

    public RRC_Integer supportedSRS_TxPortSwitch;
    public RRC_Integer txSwitchImpactToRx;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedSRS-TxPortSwitch","txSwitchImpactToRx" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedSRS_TxPortSwitch","txSwitchImpactToRx" };
    }

    @Override
    public String getAsnName() {
        return "DummyI";
    }

    @Override
    public String getXmlTagName() {
        return "DummyI";
    }

}

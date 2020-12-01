/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_BandParameters_v1540__srs_CarrierSwitch;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandParameters_v1540 extends RRC_Sequence {

    public RRC_BandParameters_v1540__srs_CarrierSwitch srs_CarrierSwitch;
    public RRC_BandParameters_v1540__srs_TxSwitch srs_TxSwitch;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-CarrierSwitch","srs-TxSwitch" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_CarrierSwitch","srs_TxSwitch" };
    }

    @Override
    public String getAsnName() {
        return "BandParameters-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "BandParameters-v1540";
    }

}

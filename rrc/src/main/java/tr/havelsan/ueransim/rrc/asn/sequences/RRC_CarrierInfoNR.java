/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;

public class RRC_CarrierInfoNR extends RRC_Sequence {

    public RRC_ARFCN_ValueNR carrierFreq;
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing;
    public RRC_SSB_MTC smtc;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","ssbSubcarrierSpacing","smtc" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","ssbSubcarrierSpacing","smtc" };
    }

    @Override
    public String getAsnName() {
        return "CarrierInfoNR";
    }

    @Override
    public String getXmlTagName() {
        return "CarrierInfoNR";
    }

}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;

public class RRC_MeasTiming__frequencyAndTiming extends RRC_Sequence {

    public RRC_ARFCN_ValueNR carrierFreq;
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing;
    public RRC_SSB_MTC ssb_MeasurementTimingConfiguration;
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","ssbSubcarrierSpacing","ssb-MeasurementTimingConfiguration","ss-RSSI-Measurement" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","ssbSubcarrierSpacing","ssb_MeasurementTimingConfiguration","ss_RSSI_Measurement" };
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

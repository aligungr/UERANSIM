/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_MeasTiming extends AsnSequence {
    public RRC_frequencyAndTiming frequencyAndTiming; // optional
    public RRC_ext1_7 ext1; // optional

    public static class RRC_ext1_7 extends AsnSequence {
        public RRC_SSB_ToMeasure ssb_ToMeasure_v1540; // optional
        public RRC_PhysCellId physCellId; // optional
    }

    public static class RRC_frequencyAndTiming extends AsnSequence {
        public RRC_ARFCN_ValueNR carrierFreq; // mandatory
        public RRC_SubcarrierSpacing ssbSubcarrierSpacing; // mandatory
        public RRC_SSB_MTC ssb_MeasurementTimingConfiguration; // mandatory
        public RRC_SS_RSSI_Measurement ss_RSSI_Measurement; // optional
    }
}


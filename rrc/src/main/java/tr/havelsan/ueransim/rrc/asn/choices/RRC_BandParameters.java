/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CA_BandwidthClassEUTRA;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CA_BandwidthClassNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_BandParameters extends AsnChoice {
    public RRC_eutra_1 eutra;
    public RRC_nr_1 nr;

    public static class RRC_nr_1 extends AsnSequence {
        public RRC_FreqBandIndicatorNR bandNR; // mandatory
        public RRC_CA_BandwidthClassNR ca_BandwidthClassDL_NR; // optional
        public RRC_CA_BandwidthClassNR ca_BandwidthClassUL_NR; // optional
    }

    public static class RRC_eutra_1 extends AsnSequence {
        public RRC_FreqBandIndicatorEUTRA bandEUTRA; // mandatory
        public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassDL_EUTRA; // optional
        public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassUL_EUTRA; // optional
    }
}


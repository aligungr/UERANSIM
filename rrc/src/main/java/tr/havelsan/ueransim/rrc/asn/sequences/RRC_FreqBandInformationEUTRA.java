/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CA_BandwidthClassEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;

public class RRC_FreqBandInformationEUTRA extends AsnSequence {
    public RRC_FreqBandIndicatorEUTRA bandEUTRA; // mandatory
    public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassDL_EUTRA; // optional
    public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassUL_EUTRA; // optional
}


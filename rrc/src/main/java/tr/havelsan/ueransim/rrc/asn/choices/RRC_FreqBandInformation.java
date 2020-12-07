/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationNR;

public class RRC_FreqBandInformation extends AsnChoice {
    public RRC_FreqBandInformationEUTRA bandInformationEUTRA;
    public RRC_FreqBandInformationNR bandInformationNR;
}


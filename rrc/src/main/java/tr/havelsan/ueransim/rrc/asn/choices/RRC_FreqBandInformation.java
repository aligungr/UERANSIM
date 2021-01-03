/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationNR;

public class RRC_FreqBandInformation extends AsnChoice {
    public RRC_FreqBandInformationEUTRA bandInformationEUTRA;
    public RRC_FreqBandInformationNR bandInformationNR;
}


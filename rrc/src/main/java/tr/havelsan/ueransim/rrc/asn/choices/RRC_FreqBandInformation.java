/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqBandInformationNR;

public class RRC_FreqBandInformation extends RRC_Choice {

    public RRC_FreqBandInformationEUTRA bandInformationEUTRA;
    public RRC_FreqBandInformationNR bandInformationNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandInformationEUTRA","bandInformationNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandInformationEUTRA","bandInformationNR" };
    }

    @Override
    public String getAsnName() {
        return "FreqBandInformation";
    }

    @Override
    public String getXmlTagName() {
        return "FreqBandInformation";
    }

}

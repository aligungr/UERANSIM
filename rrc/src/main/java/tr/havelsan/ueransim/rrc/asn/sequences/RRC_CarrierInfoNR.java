package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;

public class RRC_CarrierInfoNR extends AsnSequence {
    public RRC_ARFCN_ValueNR carrierFreq; // mandatory
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing; // mandatory
    public RRC_SSB_MTC smtc; // optional
}


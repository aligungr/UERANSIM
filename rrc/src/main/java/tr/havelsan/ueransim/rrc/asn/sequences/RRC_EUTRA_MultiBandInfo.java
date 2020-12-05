package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_NS_PmaxList;

public class RRC_EUTRA_MultiBandInfo extends AsnSequence {
    public RRC_FreqBandIndicatorEUTRA eutra_FreqBandIndicator; // mandatory
    public RRC_EUTRA_NS_PmaxList eutra_NS_PmaxList; // optional
}


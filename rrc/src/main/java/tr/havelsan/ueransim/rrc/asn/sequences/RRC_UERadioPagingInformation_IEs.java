package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_UERadioPagingInformation_IEs extends AsnSequence {
    public RRC_supportedBandListNRForPaging supportedBandListNRForPaging; // optional, SIZE(1..1024)
    public RRC_nonCriticalExtension_2 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_2 extends AsnSequence {
    }

    // SIZE(1..1024)
    public static class RRC_supportedBandListNRForPaging extends AsnSequenceOf<RRC_FreqBandIndicatorNR> {
    }
}


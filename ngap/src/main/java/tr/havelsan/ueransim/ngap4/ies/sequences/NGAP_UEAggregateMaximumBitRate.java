package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_BitRate;

public class NGAP_UEAggregateMaximumBitRate extends NgapSequence {

    public NGAP_BitRate uEAggregateMaximumBitRateDL;
    public NGAP_BitRate uEAggregateMaximumBitRateUL;

    @Override
    protected String getAsnName() {
        return "UEAggregateMaximumBitRate";
    }

    @Override
    protected String getXmlTagName() {
        return "UEAggregateMaximumBitRate";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uEAggregateMaximumBitRateDL", "uEAggregateMaximumBitRateUL"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uEAggregateMaximumBitRateDL", "uEAggregateMaximumBitRateUL"};
    }
}

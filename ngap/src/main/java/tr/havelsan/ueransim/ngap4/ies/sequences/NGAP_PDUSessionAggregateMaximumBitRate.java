package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_BitRate;

public class NGAP_PDUSessionAggregateMaximumBitRate extends NgapSequence {

    public NGAP_BitRate pDUSessionAggregateMaximumBitRateDL;
    public NGAP_BitRate pDUSessionAggregateMaximumBitRateUL;

    @Override
    protected String getAsnName() {
        return "PDUSessionAggregateMaximumBitRate";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionAggregateMaximumBitRate";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionAggregateMaximumBitRateDL", "pDUSessionAggregateMaximumBitRateUL"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionAggregateMaximumBitRateDL", "pDUSessionAggregateMaximumBitRateUL"};
    }
}

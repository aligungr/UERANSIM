package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionAggregateMaximumBitRate extends NGAP_Sequence {

    public NGAP_BitRate pDUSessionAggregateMaximumBitRateDL;
    public NGAP_BitRate pDUSessionAggregateMaximumBitRateUL;

    @Override
    public String getAsnName() {
        return "PDUSessionAggregateMaximumBitRate";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionAggregateMaximumBitRate";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionAggregateMaximumBitRateDL", "pDUSessionAggregateMaximumBitRateUL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionAggregateMaximumBitRateDL", "pDUSessionAggregateMaximumBitRateUL"};
    }
}

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_UEAggregateMaximumBitRate extends NGAP_Sequence {

    public NGAP_BitRate uEAggregateMaximumBitRateDL;
    public NGAP_BitRate uEAggregateMaximumBitRateUL;

    @Override
    public String getAsnName() {
        return "UEAggregateMaximumBitRate";
    }

    @Override
    public String getXmlTagName() {
        return "UEAggregateMaximumBitRate";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uEAggregateMaximumBitRateDL", "uEAggregateMaximumBitRateUL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uEAggregateMaximumBitRateDL", "uEAggregateMaximumBitRateUL"};
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_BitRate;

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

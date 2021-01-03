/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_OverloadResponse;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_TrafficLoadReductionIndication;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SliceOverloadList;

public class NGAP_OverloadStartNSSAIItem extends NGAP_Sequence {

    public NGAP_SliceOverloadList sliceOverloadList;
    public NGAP_OverloadResponse sliceOverloadResponse;
    public NGAP_TrafficLoadReductionIndication sliceTrafficLoadReductionIndication;

    @Override
    public String getAsnName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }
}

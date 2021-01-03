/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowWithCauseItem;

import java.util.List;

public class NGAP_QosFlowListWithCause extends NGAP_SequenceOf<NGAP_QosFlowWithCauseItem> {

    public NGAP_QosFlowListWithCause() {
        super();
    }

    public NGAP_QosFlowListWithCause(List<NGAP_QosFlowWithCauseItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowListWithCause";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowListWithCause";
    }

    @Override
    public Class<NGAP_QosFlowWithCauseItem> getItemType() {
        return NGAP_QosFlowWithCauseItem.class;
    }
}

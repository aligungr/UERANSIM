/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowNotifyItem;

import java.util.List;

public class NGAP_QosFlowNotifyList extends NGAP_SequenceOf<NGAP_QosFlowNotifyItem> {

    public NGAP_QosFlowNotifyList() {
        super();
    }

    public NGAP_QosFlowNotifyList(List<NGAP_QosFlowNotifyItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowNotifyList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowNotifyList";
    }

    @Override
    public Class<NGAP_QosFlowNotifyItem> getItemType() {
        return NGAP_QosFlowNotifyItem.class;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AssociatedQosFlowItem;

import java.util.List;

public class NGAP_AssociatedQosFlowList extends NGAP_SequenceOf<NGAP_AssociatedQosFlowItem> {

    public NGAP_AssociatedQosFlowList() {
        super();
    }

    public NGAP_AssociatedQosFlowList(List<NGAP_AssociatedQosFlowItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AssociatedQosFlowList";
    }

    @Override
    public String getXmlTagName() {
        return "AssociatedQosFlowList";
    }

    @Override
    public Class<NGAP_AssociatedQosFlowItem> getItemType() {
        return NGAP_AssociatedQosFlowItem.class;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowModifyConfirmItem;

import java.util.List;

public class NGAP_QosFlowModifyConfirmList extends NGAP_SequenceOf<NGAP_QosFlowModifyConfirmItem> {

    public NGAP_QosFlowModifyConfirmList() {
        super();
    }

    public NGAP_QosFlowModifyConfirmList(List<NGAP_QosFlowModifyConfirmItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public Class<NGAP_QosFlowModifyConfirmItem> getItemType() {
        return NGAP_QosFlowModifyConfirmItem.class;
    }
}

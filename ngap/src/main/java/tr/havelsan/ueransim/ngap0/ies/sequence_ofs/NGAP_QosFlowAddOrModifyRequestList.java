/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowAddOrModifyRequestItem;

import java.util.List;

public class NGAP_QosFlowAddOrModifyRequestList extends NGAP_SequenceOf<NGAP_QosFlowAddOrModifyRequestItem> {

    public NGAP_QosFlowAddOrModifyRequestList() {
        super();
    }

    public NGAP_QosFlowAddOrModifyRequestList(List<NGAP_QosFlowAddOrModifyRequestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyRequestItem> getItemType() {
        return NGAP_QosFlowAddOrModifyRequestItem.class;
    }
}

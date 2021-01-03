/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowAddOrModifyResponseItem;

import java.util.List;

public class NGAP_QosFlowAddOrModifyResponseList extends NGAP_SequenceOf<NGAP_QosFlowAddOrModifyResponseItem> {

    public NGAP_QosFlowAddOrModifyResponseList() {
        super();
    }

    public NGAP_QosFlowAddOrModifyResponseList(List<NGAP_QosFlowAddOrModifyResponseItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyResponseItem> getItemType() {
        return NGAP_QosFlowAddOrModifyResponseItem.class;
    }
}

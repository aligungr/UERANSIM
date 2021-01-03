/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowToBeForwardedItem;

import java.util.List;

public class NGAP_QosFlowToBeForwardedList extends NGAP_SequenceOf<NGAP_QosFlowToBeForwardedItem> {

    public NGAP_QosFlowToBeForwardedList() {
        super();
    }

    public NGAP_QosFlowToBeForwardedList(List<NGAP_QosFlowToBeForwardedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    public Class<NGAP_QosFlowToBeForwardedItem> getItemType() {
        return NGAP_QosFlowToBeForwardedItem.class;
    }
}

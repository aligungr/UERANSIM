/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowInformationItem;

import java.util.List;

public class NGAP_QosFlowInformationList extends NGAP_SequenceOf<NGAP_QosFlowInformationItem> {

    public NGAP_QosFlowInformationList() {
        super();
    }

    public NGAP_QosFlowInformationList(List<NGAP_QosFlowInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowInformationList";
    }

    @Override
    public Class<NGAP_QosFlowInformationItem> getItemType() {
        return NGAP_QosFlowInformationItem.class;
    }
}

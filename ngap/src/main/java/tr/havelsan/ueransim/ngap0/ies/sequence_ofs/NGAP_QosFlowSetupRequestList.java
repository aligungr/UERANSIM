/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowSetupRequestItem;

import java.util.List;

public class NGAP_QosFlowSetupRequestList extends NGAP_SequenceOf<NGAP_QosFlowSetupRequestItem> {

    public NGAP_QosFlowSetupRequestList() {
        super();
    }

    public NGAP_QosFlowSetupRequestList(List<NGAP_QosFlowSetupRequestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public Class<NGAP_QosFlowSetupRequestItem> getItemType() {
        return NGAP_QosFlowSetupRequestItem.class;
    }
}

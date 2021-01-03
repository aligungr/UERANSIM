/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowItemWithDataForwarding;

import java.util.List;

public class NGAP_QosFlowListWithDataForwarding extends NGAP_SequenceOf<NGAP_QosFlowItemWithDataForwarding> {

    public NGAP_QosFlowListWithDataForwarding() {
        super();
    }

    public NGAP_QosFlowListWithDataForwarding(List<NGAP_QosFlowItemWithDataForwarding> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowListWithDataForwarding";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowListWithDataForwarding";
    }

    @Override
    public Class<NGAP_QosFlowItemWithDataForwarding> getItemType() {
        return NGAP_QosFlowItemWithDataForwarding.class;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QoSFlowsUsageReport_Item;

import java.util.List;

public class NGAP_QoSFlowsUsageReportList extends NGAP_SequenceOf<NGAP_QoSFlowsUsageReport_Item> {

    public NGAP_QoSFlowsUsageReportList() {
        super();
    }

    public NGAP_QoSFlowsUsageReportList(List<NGAP_QoSFlowsUsageReport_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QoSFlowsUsageReportList";
    }

    @Override
    public String getXmlTagName() {
        return "QoSFlowsUsageReportList";
    }

    @Override
    public Class<NGAP_QoSFlowsUsageReport_Item> getItemType() {
        return NGAP_QoSFlowsUsageReport_Item.class;
    }
}

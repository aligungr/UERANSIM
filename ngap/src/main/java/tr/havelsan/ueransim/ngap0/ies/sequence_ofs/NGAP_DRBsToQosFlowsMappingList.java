/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DRBsToQosFlowsMappingItem;

import java.util.List;

public class NGAP_DRBsToQosFlowsMappingList extends NGAP_SequenceOf<NGAP_DRBsToQosFlowsMappingItem> {

    public NGAP_DRBsToQosFlowsMappingList() {
        super();
    }

    public NGAP_DRBsToQosFlowsMappingList(List<NGAP_DRBsToQosFlowsMappingItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "DRBsToQosFlowsMappingList";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsToQosFlowsMappingList";
    }

    @Override
    public Class<NGAP_DRBsToQosFlowsMappingItem> getItemType() {
        return NGAP_DRBsToQosFlowsMappingItem.class;
    }
}

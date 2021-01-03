/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_OverloadStartNSSAIItem;

import java.util.List;

public class NGAP_OverloadStartNSSAIList extends NGAP_SequenceOf<NGAP_OverloadStartNSSAIItem> {

    public NGAP_OverloadStartNSSAIList() {
        super();
    }

    public NGAP_OverloadStartNSSAIList(List<NGAP_OverloadStartNSSAIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    public Class<NGAP_OverloadStartNSSAIItem> getItemType() {
        return NGAP_OverloadStartNSSAIItem.class;
    }
}

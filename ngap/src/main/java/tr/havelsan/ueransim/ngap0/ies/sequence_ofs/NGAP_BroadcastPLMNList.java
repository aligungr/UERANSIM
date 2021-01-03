/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_BroadcastPLMNItem;

import java.util.List;

public class NGAP_BroadcastPLMNList extends NGAP_SequenceOf<NGAP_BroadcastPLMNItem> {

    public NGAP_BroadcastPLMNList() {
        super();
    }

    public NGAP_BroadcastPLMNList(List<NGAP_BroadcastPLMNItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "BroadcastPLMNList";
    }

    @Override
    public String getXmlTagName() {
        return "BroadcastPLMNList";
    }

    @Override
    public Class<NGAP_BroadcastPLMNItem> getItemType() {
        return NGAP_BroadcastPLMNItem.class;
    }
}

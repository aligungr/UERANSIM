/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PLMNSupportItem;

import java.util.List;

public class NGAP_PLMNSupportList extends NGAP_SequenceOf<NGAP_PLMNSupportItem> {

    public NGAP_PLMNSupportList() {
        super();
    }

    public NGAP_PLMNSupportList(List<NGAP_PLMNSupportItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PLMNSupportList";
    }

    @Override
    public String getXmlTagName() {
        return "PLMNSupportList";
    }

    @Override
    public Class<NGAP_PLMNSupportItem> getItemType() {
        return NGAP_PLMNSupportItem.class;
    }
}

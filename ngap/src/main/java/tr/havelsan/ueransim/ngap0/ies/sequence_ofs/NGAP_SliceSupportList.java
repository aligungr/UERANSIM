/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SliceSupportItem;

import java.util.List;

public class NGAP_SliceSupportList extends NGAP_SequenceOf<NGAP_SliceSupportItem> {

    public NGAP_SliceSupportList() {
        super();
    }

    public NGAP_SliceSupportList(List<NGAP_SliceSupportItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SliceSupportList";
    }

    @Override
    public String getXmlTagName() {
        return "SliceSupportList";
    }

    @Override
    public Class<NGAP_SliceSupportItem> getItemType() {
        return NGAP_SliceSupportItem.class;
    }
}

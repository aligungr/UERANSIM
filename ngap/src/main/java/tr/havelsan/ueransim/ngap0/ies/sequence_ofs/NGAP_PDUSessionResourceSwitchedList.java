/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSwitchedItem;

import java.util.List;

public class NGAP_PDUSessionResourceSwitchedList extends NGAP_SequenceOf<NGAP_PDUSessionResourceSwitchedItem> {

    public NGAP_PDUSessionResourceSwitchedList() {
        super();
    }

    public NGAP_PDUSessionResourceSwitchedList(List<NGAP_PDUSessionResourceSwitchedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSwitchedItem> getItemType() {
        return NGAP_PDUSessionResourceSwitchedItem.class;
    }
}

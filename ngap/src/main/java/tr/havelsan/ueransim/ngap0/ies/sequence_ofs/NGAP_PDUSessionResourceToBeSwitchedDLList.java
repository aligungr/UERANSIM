/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceToBeSwitchedDLItem;

import java.util.List;

public class NGAP_PDUSessionResourceToBeSwitchedDLList extends NGAP_SequenceOf<NGAP_PDUSessionResourceToBeSwitchedDLItem> {

    public NGAP_PDUSessionResourceToBeSwitchedDLList() {
        super();
    }

    public NGAP_PDUSessionResourceToBeSwitchedDLList(List<NGAP_PDUSessionResourceToBeSwitchedDLItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToBeSwitchedDLItem> getItemType() {
        return NGAP_PDUSessionResourceToBeSwitchedDLItem.class;
    }
}

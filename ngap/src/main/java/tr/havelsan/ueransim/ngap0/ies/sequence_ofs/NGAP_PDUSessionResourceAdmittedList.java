/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceAdmittedItem;

import java.util.List;

public class NGAP_PDUSessionResourceAdmittedList extends NGAP_SequenceOf<NGAP_PDUSessionResourceAdmittedItem> {

    public NGAP_PDUSessionResourceAdmittedList() {
        super();
    }

    public NGAP_PDUSessionResourceAdmittedList(List<NGAP_PDUSessionResourceAdmittedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceAdmittedItem> getItemType() {
        return NGAP_PDUSessionResourceAdmittedItem.class;
    }
}

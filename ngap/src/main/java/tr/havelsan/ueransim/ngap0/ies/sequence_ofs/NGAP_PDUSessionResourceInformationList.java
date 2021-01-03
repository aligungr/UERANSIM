/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceInformationItem;

import java.util.List;

public class NGAP_PDUSessionResourceInformationList extends NGAP_SequenceOf<NGAP_PDUSessionResourceInformationItem> {

    public NGAP_PDUSessionResourceInformationList() {
        super();
    }

    public NGAP_PDUSessionResourceInformationList(List<NGAP_PDUSessionResourceInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceInformationItem> getItemType() {
        return NGAP_PDUSessionResourceInformationItem.class;
    }
}

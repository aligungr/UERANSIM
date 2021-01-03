/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceReleasedItemNot;

import java.util.List;

public class NGAP_PDUSessionResourceReleasedListNot extends NGAP_SequenceOf<NGAP_PDUSessionResourceReleasedItemNot> {

    public NGAP_PDUSessionResourceReleasedListNot() {
        super();
    }

    public NGAP_PDUSessionResourceReleasedListNot(List<NGAP_PDUSessionResourceReleasedItemNot> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemNot> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemNot.class;
    }
}

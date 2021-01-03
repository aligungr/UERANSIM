/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceReleasedItemPSFail;

import java.util.List;

public class NGAP_PDUSessionResourceReleasedListPSFail extends NGAP_SequenceOf<NGAP_PDUSessionResourceReleasedItemPSFail> {

    public NGAP_PDUSessionResourceReleasedListPSFail() {
        super();
    }

    public NGAP_PDUSessionResourceReleasedListPSFail(List<NGAP_PDUSessionResourceReleasedItemPSFail> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemPSFail> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemPSFail.class;
    }
}

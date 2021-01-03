/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceItemCxtRelReq;

import java.util.List;

public class NGAP_PDUSessionResourceListCxtRelReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemCxtRelReq> {

    public NGAP_PDUSessionResourceListCxtRelReq() {
        super();
    }

    public NGAP_PDUSessionResourceListCxtRelReq(List<NGAP_PDUSessionResourceItemCxtRelReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelReq> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelReq.class;
    }
}

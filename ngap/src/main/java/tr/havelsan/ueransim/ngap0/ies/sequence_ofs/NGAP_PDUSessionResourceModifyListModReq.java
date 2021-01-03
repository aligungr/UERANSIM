/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceModifyItemModReq;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModReq> {

    public NGAP_PDUSessionResourceModifyListModReq() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModReq(List<NGAP_PDUSessionResourceModifyItemModReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModReq> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModReq.class;
    }
}

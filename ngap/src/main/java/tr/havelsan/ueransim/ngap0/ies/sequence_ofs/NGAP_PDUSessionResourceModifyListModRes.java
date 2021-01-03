/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceModifyItemModRes;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModRes> {

    public NGAP_PDUSessionResourceModifyListModRes() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModRes(List<NGAP_PDUSessionResourceModifyItemModRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModRes.class;
    }
}

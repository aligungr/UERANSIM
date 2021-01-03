/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceModifyItemModCfm;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModCfm> {

    public NGAP_PDUSessionResourceModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModCfm(List<NGAP_PDUSessionResourceModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModCfm.class;
    }
}

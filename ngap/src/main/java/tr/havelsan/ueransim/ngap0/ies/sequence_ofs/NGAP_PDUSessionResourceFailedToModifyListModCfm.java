/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceFailedToModifyItemModCfm;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModCfm> {

    public NGAP_PDUSessionResourceFailedToModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToModifyListModCfm(List<NGAP_PDUSessionResourceFailedToModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModCfm.class;
    }
}

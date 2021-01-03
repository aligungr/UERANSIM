/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemSURes;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListSURes extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemSURes> {

    public NGAP_PDUSessionResourceFailedToSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListSURes(List<NGAP_PDUSessionResourceFailedToSetupItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemSURes.class;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupItemSURes;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListSURes extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemSURes> {

    public NGAP_PDUSessionResourceSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListSURes(List<NGAP_PDUSessionResourceSetupItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSURes.class;
    }
}

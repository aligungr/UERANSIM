/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupItemSUReq;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListSUReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemSUReq> {

    public NGAP_PDUSessionResourceSetupListSUReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListSUReq(List<NGAP_PDUSessionResourceSetupItemSUReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSUReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSUReq.class;
    }
}

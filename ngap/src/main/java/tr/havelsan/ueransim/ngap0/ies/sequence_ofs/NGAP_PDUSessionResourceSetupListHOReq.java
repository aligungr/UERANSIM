/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupItemHOReq;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListHOReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemHOReq> {

    public NGAP_PDUSessionResourceSetupListHOReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListHOReq(List<NGAP_PDUSessionResourceSetupItemHOReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemHOReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemHOReq.class;
    }
}

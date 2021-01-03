/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupItemCxtReq;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListCxtReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemCxtReq> {

    public NGAP_PDUSessionResourceSetupListCxtReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListCxtReq(List<NGAP_PDUSessionResourceSetupItemCxtReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtReq.class;
    }
}

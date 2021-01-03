/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemPSReq;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListPSReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemPSReq> {

    public NGAP_PDUSessionResourceFailedToSetupListPSReq() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListPSReq(List<NGAP_PDUSessionResourceFailedToSetupItemPSReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemPSReq> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemPSReq.class;
    }
}

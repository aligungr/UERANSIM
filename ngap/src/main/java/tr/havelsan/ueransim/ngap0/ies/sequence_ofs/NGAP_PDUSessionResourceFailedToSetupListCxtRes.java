/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemCxtRes;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListCxtRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> {

    public NGAP_PDUSessionResourceFailedToSetupListCxtRes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListCxtRes(List<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtRes.class;
    }
}

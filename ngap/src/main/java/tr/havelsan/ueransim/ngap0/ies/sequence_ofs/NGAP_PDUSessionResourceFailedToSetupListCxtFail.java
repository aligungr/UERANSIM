/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemCxtFail;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListCxtFail extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> {

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail(List<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtFail.class;
    }
}

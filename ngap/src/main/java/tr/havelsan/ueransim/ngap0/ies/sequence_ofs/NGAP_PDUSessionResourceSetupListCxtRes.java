/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupItemCxtRes;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListCxtRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemCxtRes> {

    public NGAP_PDUSessionResourceSetupListCxtRes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListCxtRes(List<NGAP_PDUSessionResourceSetupItemCxtRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtRes.class;
    }
}

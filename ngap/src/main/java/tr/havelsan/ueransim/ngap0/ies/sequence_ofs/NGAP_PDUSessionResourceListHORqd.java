/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceItemHORqd;

import java.util.List;

public class NGAP_PDUSessionResourceListHORqd extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemHORqd> {

    public NGAP_PDUSessionResourceListHORqd() {
        super();
    }

    public NGAP_PDUSessionResourceListHORqd(List<NGAP_PDUSessionResourceItemHORqd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemHORqd> getItemType() {
        return NGAP_PDUSessionResourceItemHORqd.class;
    }
}

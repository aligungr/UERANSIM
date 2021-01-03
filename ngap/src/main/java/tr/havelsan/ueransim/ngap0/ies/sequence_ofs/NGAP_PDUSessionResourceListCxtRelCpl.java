/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceItemCxtRelCpl;

import java.util.List;

public class NGAP_PDUSessionResourceListCxtRelCpl extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemCxtRelCpl> {

    public NGAP_PDUSessionResourceListCxtRelCpl() {
        super();
    }

    public NGAP_PDUSessionResourceListCxtRelCpl(List<NGAP_PDUSessionResourceItemCxtRelCpl> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelCpl> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelCpl.class;
    }
}

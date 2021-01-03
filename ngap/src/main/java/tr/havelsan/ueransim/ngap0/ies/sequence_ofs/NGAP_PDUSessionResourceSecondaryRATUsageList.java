/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSecondaryRATUsageItem;

import java.util.List;

public class NGAP_PDUSessionResourceSecondaryRATUsageList extends NGAP_SequenceOf<NGAP_PDUSessionResourceSecondaryRATUsageItem> {

    public NGAP_PDUSessionResourceSecondaryRATUsageList() {
        super();
    }

    public NGAP_PDUSessionResourceSecondaryRATUsageList(List<NGAP_PDUSessionResourceSecondaryRATUsageItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSecondaryRATUsageList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSecondaryRATUsageList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSecondaryRATUsageItem> getItemType() {
        return NGAP_PDUSessionResourceSecondaryRATUsageItem.class;
    }
}

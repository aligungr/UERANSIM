/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_E_RABInformationItem;

import java.util.List;

public class NGAP_E_RABInformationList extends NGAP_SequenceOf<NGAP_E_RABInformationItem> {

    public NGAP_E_RABInformationList() {
        super();
    }

    public NGAP_E_RABInformationList(List<NGAP_E_RABInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "E-RABInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "E-RABInformationList";
    }

    @Override
    public Class<NGAP_E_RABInformationItem> getItemType() {
        return NGAP_E_RABInformationItem.class;
    }
}

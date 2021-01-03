/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UnavailableGUAMIItem;

import java.util.List;

public class NGAP_UnavailableGUAMIList extends NGAP_SequenceOf<NGAP_UnavailableGUAMIItem> {

    public NGAP_UnavailableGUAMIList() {
        super();
    }

    public NGAP_UnavailableGUAMIList(List<NGAP_UnavailableGUAMIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UnavailableGUAMIList";
    }

    @Override
    public String getXmlTagName() {
        return "UnavailableGUAMIList";
    }

    @Override
    public Class<NGAP_UnavailableGUAMIItem> getItemType() {
        return NGAP_UnavailableGUAMIItem.class;
    }
}

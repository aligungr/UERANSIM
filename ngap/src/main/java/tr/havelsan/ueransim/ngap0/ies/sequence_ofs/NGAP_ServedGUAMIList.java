/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_ServedGUAMIItem;

import java.util.List;

public class NGAP_ServedGUAMIList extends NGAP_SequenceOf<NGAP_ServedGUAMIItem> {

    public NGAP_ServedGUAMIList() {
        super();
    }

    public NGAP_ServedGUAMIList(List<NGAP_ServedGUAMIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ServedGUAMIList";
    }

    @Override
    public String getXmlTagName() {
        return "ServedGUAMIList";
    }

    @Override
    public Class<NGAP_ServedGUAMIItem> getItemType() {
        return NGAP_ServedGUAMIItem.class;
    }
}

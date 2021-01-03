/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TAIListForInactiveItem;

import java.util.List;

public class NGAP_TAIListForInactive extends NGAP_SequenceOf<NGAP_TAIListForInactiveItem> {

    public NGAP_TAIListForInactive() {
        super();
    }

    public NGAP_TAIListForInactive(List<NGAP_TAIListForInactiveItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForInactive";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForInactive";
    }

    @Override
    public Class<NGAP_TAIListForInactiveItem> getItemType() {
        return NGAP_TAIListForInactiveItem.class;
    }
}

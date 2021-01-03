/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UL_NGU_UP_TNLModifyItem;

import java.util.List;

public class NGAP_UL_NGU_UP_TNLModifyList extends NGAP_SequenceOf<NGAP_UL_NGU_UP_TNLModifyItem> {

    public NGAP_UL_NGU_UP_TNLModifyList() {
        super();
    }

    public NGAP_UL_NGU_UP_TNLModifyList(List<NGAP_UL_NGU_UP_TNLModifyItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    public String getXmlTagName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    public Class<NGAP_UL_NGU_UP_TNLModifyItem> getItemType() {
        return NGAP_UL_NGU_UP_TNLModifyItem.class;
    }
}

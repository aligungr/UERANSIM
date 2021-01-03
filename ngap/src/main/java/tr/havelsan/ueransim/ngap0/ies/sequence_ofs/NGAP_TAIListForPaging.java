/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TAIListForPagingItem;

import java.util.List;

public class NGAP_TAIListForPaging extends NGAP_SequenceOf<NGAP_TAIListForPagingItem> {

    public NGAP_TAIListForPaging() {
        super();
    }

    public NGAP_TAIListForPaging(List<NGAP_TAIListForPagingItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForPaging";
    }

    @Override
    public Class<NGAP_TAIListForPagingItem> getItemType() {
        return NGAP_TAIListForPagingItem.class;
    }
}

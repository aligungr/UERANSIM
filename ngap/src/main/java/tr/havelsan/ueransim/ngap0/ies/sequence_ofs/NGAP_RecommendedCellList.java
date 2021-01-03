/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_RecommendedCellItem;

import java.util.List;

public class NGAP_RecommendedCellList extends NGAP_SequenceOf<NGAP_RecommendedCellItem> {

    public NGAP_RecommendedCellList() {
        super();
    }

    public NGAP_RecommendedCellList(List<NGAP_RecommendedCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RecommendedCellList";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedCellList";
    }

    @Override
    public Class<NGAP_RecommendedCellItem> getItemType() {
        return NGAP_RecommendedCellItem.class;
    }
}

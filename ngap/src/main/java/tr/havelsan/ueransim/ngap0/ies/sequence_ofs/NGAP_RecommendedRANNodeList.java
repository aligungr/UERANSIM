/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_RecommendedRANNodeItem;

import java.util.List;

public class NGAP_RecommendedRANNodeList extends NGAP_SequenceOf<NGAP_RecommendedRANNodeItem> {

    public NGAP_RecommendedRANNodeList() {
        super();
    }

    public NGAP_RecommendedRANNodeList(List<NGAP_RecommendedRANNodeItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public Class<NGAP_RecommendedRANNodeItem> getItemType() {
        return NGAP_RecommendedRANNodeItem.class;
    }
}

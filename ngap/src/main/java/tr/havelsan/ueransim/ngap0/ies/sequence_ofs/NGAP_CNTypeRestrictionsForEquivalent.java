/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CNTypeRestrictionsForEquivalentItem;

import java.util.List;

public class NGAP_CNTypeRestrictionsForEquivalent extends NGAP_SequenceOf<NGAP_CNTypeRestrictionsForEquivalentItem> {

    public NGAP_CNTypeRestrictionsForEquivalent() {
        super();
    }

    public NGAP_CNTypeRestrictionsForEquivalent(List<NGAP_CNTypeRestrictionsForEquivalentItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CNTypeRestrictionsForEquivalent";
    }

    @Override
    public String getXmlTagName() {
        return "CNTypeRestrictionsForEquivalent";
    }

    @Override
    public Class<NGAP_CNTypeRestrictionsForEquivalentItem> getItemType() {
        return NGAP_CNTypeRestrictionsForEquivalentItem.class;
    }
}

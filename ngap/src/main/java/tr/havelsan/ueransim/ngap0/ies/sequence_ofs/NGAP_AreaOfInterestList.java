/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AreaOfInterestItem;

import java.util.List;

public class NGAP_AreaOfInterestList extends NGAP_SequenceOf<NGAP_AreaOfInterestItem> {

    public NGAP_AreaOfInterestList() {
        super();
    }

    public NGAP_AreaOfInterestList(List<NGAP_AreaOfInterestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestList";
    }

    @Override
    public Class<NGAP_AreaOfInterestItem> getItemType() {
        return NGAP_AreaOfInterestItem.class;
    }
}

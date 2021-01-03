/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AreaOfInterestCellItem;

import java.util.List;

public class NGAP_AreaOfInterestCellList extends NGAP_SequenceOf<NGAP_AreaOfInterestCellItem> {

    public NGAP_AreaOfInterestCellList() {
        super();
    }

    public NGAP_AreaOfInterestCellList(List<NGAP_AreaOfInterestCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestCellList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestCellList";
    }

    @Override
    public Class<NGAP_AreaOfInterestCellItem> getItemType() {
        return NGAP_AreaOfInterestCellItem.class;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_LastVisitedCellItem;

import java.util.List;

public class NGAP_UEHistoryInformation extends NGAP_SequenceOf<NGAP_LastVisitedCellItem> {

    public NGAP_UEHistoryInformation() {
        super();
    }

    public NGAP_UEHistoryInformation(List<NGAP_LastVisitedCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UEHistoryInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UEHistoryInformation";
    }

    @Override
    public Class<NGAP_LastVisitedCellItem> getItemType() {
        return NGAP_LastVisitedCellItem.class;
    }
}

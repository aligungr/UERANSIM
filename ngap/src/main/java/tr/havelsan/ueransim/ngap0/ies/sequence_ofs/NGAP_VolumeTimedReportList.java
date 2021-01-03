/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_VolumeTimedReport_Item;

import java.util.List;

public class NGAP_VolumeTimedReportList extends NGAP_SequenceOf<NGAP_VolumeTimedReport_Item> {

    public NGAP_VolumeTimedReportList() {
        super();
    }

    public NGAP_VolumeTimedReportList(List<NGAP_VolumeTimedReport_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "VolumeTimedReportList";
    }

    @Override
    public String getXmlTagName() {
        return "VolumeTimedReportList";
    }

    @Override
    public Class<NGAP_VolumeTimedReport_Item> getItemType() {
        return NGAP_VolumeTimedReport_Item.class;
    }
}

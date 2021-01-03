/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AveragingWindow;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_FiveQI;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_MaximumDataBurstVolume;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PriorityLevelQos;

public class NGAP_NonDynamic5QIDescriptor extends NGAP_Sequence {

    public NGAP_FiveQI fiveQI;
    public NGAP_PriorityLevelQos priorityLevelQos;
    public NGAP_AveragingWindow averagingWindow;
    public NGAP_MaximumDataBurstVolume maximumDataBurstVolume;

    @Override
    public String getAsnName() {
        return "NonDynamic5QIDescriptor";
    }

    @Override
    public String getXmlTagName() {
        return "NonDynamic5QIDescriptor";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"fiveQI", "priorityLevelQos", "averagingWindow", "maximumDataBurstVolume"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"fiveQI", "priorityLevelQos", "averagingWindow", "maximumDataBurstVolume"};
    }
}

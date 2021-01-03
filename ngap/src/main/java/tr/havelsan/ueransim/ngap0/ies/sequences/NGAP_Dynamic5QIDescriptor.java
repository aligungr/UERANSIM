/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DelayCritical;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_Dynamic5QIDescriptor extends NGAP_Sequence {

    public NGAP_PriorityLevelQos priorityLevelQos;
    public NGAP_PacketDelayBudget packetDelayBudget;
    public NGAP_PacketErrorRate packetErrorRate;
    public NGAP_FiveQI fiveQI;
    public NGAP_DelayCritical delayCritical;
    public NGAP_AveragingWindow averagingWindow;
    public NGAP_MaximumDataBurstVolume maximumDataBurstVolume;

    @Override
    public String getAsnName() {
        return "Dynamic5QIDescriptor";
    }

    @Override
    public String getXmlTagName() {
        return "Dynamic5QIDescriptor";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"priorityLevelQos", "packetDelayBudget", "packetErrorRate", "fiveQI", "delayCritical", "averagingWindow", "maximumDataBurstVolume"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"priorityLevelQos", "packetDelayBudget", "packetErrorRate", "fiveQI", "delayCritical", "averagingWindow", "maximumDataBurstVolume"};
    }
}

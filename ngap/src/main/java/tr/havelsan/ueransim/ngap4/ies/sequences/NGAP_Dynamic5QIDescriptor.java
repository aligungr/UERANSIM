package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_DelayCritical;
import tr.havelsan.ueransim.ngap4.ies.integers.*;

public class NGAP_Dynamic5QIDescriptor extends NgapSequence {

    public NGAP_PriorityLevelQos priorityLevelQos;
    public NGAP_PacketDelayBudget packetDelayBudget;
    public NGAP_PacketErrorRate packetErrorRate;
    public NGAP_FiveQI fiveQI;
    public NGAP_DelayCritical delayCritical;
    public NGAP_AveragingWindow averagingWindow;
    public NGAP_MaximumDataBurstVolume maximumDataBurstVolume;

    @Override
    protected String getAsnName() {
        return "Dynamic5QIDescriptor";
    }

    @Override
    protected String getXmlTagName() {
        return "Dynamic5QIDescriptor";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"priorityLevelQos", "packetDelayBudget", "packetErrorRate", "fiveQI", "delayCritical", "averagingWindow", "maximumDataBurstVolume"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"priorityLevelQos", "packetDelayBudget", "packetErrorRate", "fiveQI", "delayCritical", "averagingWindow", "maximumDataBurstVolume"};
    }
}

package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_AveragingWindow;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_FiveQI;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_MaximumDataBurstVolume;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PriorityLevelQos;

public class NGAP_NonDynamic5QIDescriptor extends NgapSequence {

    public NGAP_FiveQI fiveQI;
    public NGAP_PriorityLevelQos priorityLevelQos;
    public NGAP_AveragingWindow averagingWindow;
    public NGAP_MaximumDataBurstVolume maximumDataBurstVolume;

    @Override
    protected String getAsnName() {
        return "NonDynamic5QIDescriptor";
    }

    @Override
    protected String getXmlTagName() {
        return "NonDynamic5QIDescriptor";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"fiveQI", "priorityLevelQos", "averagingWindow", "maximumDataBurstVolume"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"fiveQI", "priorityLevelQos", "averagingWindow", "maximumDataBurstVolume"};
    }
}

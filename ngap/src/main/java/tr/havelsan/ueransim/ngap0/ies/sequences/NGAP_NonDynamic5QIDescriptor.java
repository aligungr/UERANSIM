package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

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

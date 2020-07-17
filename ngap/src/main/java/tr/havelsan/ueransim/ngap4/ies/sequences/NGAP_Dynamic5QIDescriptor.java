package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_Dynamic5QIDescriptor extends NgapSequence {

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

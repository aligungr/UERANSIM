package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_Pre_emptionCapability;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_Pre_emptionVulnerability;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PriorityLevelARP;

public class NGAP_AllocationAndRetentionPriority extends NgapSequence {

    public NGAP_PriorityLevelARP priorityLevelARP;
    public NGAP_Pre_emptionCapability pre_emptionCapability;
    public NGAP_Pre_emptionVulnerability pre_emptionVulnerability;

    @Override
    protected String getAsnName() {
        return "AllocationAndRetentionPriority";
    }

    @Override
    protected String getXmlTagName() {
        return "AllocationAndRetentionPriority";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"priorityLevelARP", "pre-emptionCapability", "pre-emptionVulnerability"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"priorityLevelARP", "pre_emptionCapability", "pre_emptionVulnerability"};
    }
}

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

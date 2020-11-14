/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_AllocationAndRetentionPriority extends NGAP_Sequence {

    public NGAP_PriorityLevelARP priorityLevelARP;
    public NGAP_Pre_emptionCapability pre_emptionCapability;
    public NGAP_Pre_emptionVulnerability pre_emptionVulnerability;

    @Override
    public String getAsnName() {
        return "AllocationAndRetentionPriority";
    }

    @Override
    public String getXmlTagName() {
        return "AllocationAndRetentionPriority";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"priorityLevelARP", "pre-emptionCapability", "pre-emptionVulnerability"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"priorityLevelARP", "pre_emptionCapability", "pre_emptionVulnerability"};
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_Pre_emptionCapability;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_Pre_emptionVulnerability;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PriorityLevelARP;

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

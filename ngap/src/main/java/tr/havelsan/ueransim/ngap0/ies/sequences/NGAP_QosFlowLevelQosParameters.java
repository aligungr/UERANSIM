/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_QosCharacteristics;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_AdditionalQosFlowInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_ReflectiveQosAttribute;

public class NGAP_QosFlowLevelQosParameters extends NGAP_Sequence {

    public NGAP_QosCharacteristics qosCharacteristics;
    public NGAP_AllocationAndRetentionPriority allocationAndRetentionPriority;
    public NGAP_GBR_QosInformation gBR_QosInformation;
    public NGAP_ReflectiveQosAttribute reflectiveQosAttribute;
    public NGAP_AdditionalQosFlowInformation additionalQosFlowInformation;

    @Override
    public String getAsnName() {
        return "QosFlowLevelQosParameters";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowLevelQosParameters";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosCharacteristics", "allocationAndRetentionPriority", "gBR-QosInformation", "reflectiveQosAttribute", "additionalQosFlowInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosCharacteristics", "allocationAndRetentionPriority", "gBR_QosInformation", "reflectiveQosAttribute", "additionalQosFlowInformation"};
    }
}

package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_QosCharacteristics;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_AdditionalQosFlowInformation;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ReflectiveQosAttribute;

public class NGAP_QosFlowLevelQosParameters extends NgapSequence {

    public NGAP_QosCharacteristics qosCharacteristics;
    public NGAP_AllocationAndRetentionPriority allocationAndRetentionPriority;
    public NGAP_GBR_QosInformation gBR_QosInformation;
    public NGAP_ReflectiveQosAttribute reflectiveQosAttribute;
    public NGAP_AdditionalQosFlowInformation additionalQosFlowInformation;

    @Override
    protected String getAsnName() {
        return "QosFlowLevelQosParameters";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowLevelQosParameters";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosCharacteristics", "allocationAndRetentionPriority", "gBR-QosInformation", "reflectiveQosAttribute", "additionalQosFlowInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosCharacteristics", "allocationAndRetentionPriority", "gBR_QosInformation", "reflectiveQosAttribute", "additionalQosFlowInformation"};
    }
}

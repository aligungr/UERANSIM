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

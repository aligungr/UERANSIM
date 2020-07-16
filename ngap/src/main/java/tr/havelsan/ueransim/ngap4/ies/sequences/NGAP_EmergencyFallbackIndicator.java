package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_EmergencyFallbackRequestIndicator;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_EmergencyServiceTargetCN;

public class NGAP_EmergencyFallbackIndicator extends NgapSequence {

    public NGAP_EmergencyFallbackRequestIndicator emergencyFallbackRequestIndicator;
    public NGAP_EmergencyServiceTargetCN emergencyServiceTargetCN;

    @Override
    protected String getAsnName() {
        return "EmergencyFallbackIndicator";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyFallbackIndicator";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyFallbackRequestIndicator", "emergencyServiceTargetCN"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyFallbackRequestIndicator", "emergencyServiceTargetCN"};
    }
}

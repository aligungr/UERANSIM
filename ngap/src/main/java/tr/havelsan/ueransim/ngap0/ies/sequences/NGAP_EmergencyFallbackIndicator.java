package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_EmergencyFallbackIndicator extends NGAP_Sequence {

    public NGAP_EmergencyFallbackRequestIndicator emergencyFallbackRequestIndicator;
    public NGAP_EmergencyServiceTargetCN emergencyServiceTargetCN;

    @Override
    public String getAsnName() {
        return "EmergencyFallbackIndicator";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyFallbackIndicator";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyFallbackRequestIndicator", "emergencyServiceTargetCN"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyFallbackRequestIndicator", "emergencyServiceTargetCN"};
    }
}

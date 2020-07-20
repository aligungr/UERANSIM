package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_EmergencyFallbackRequestIndicator extends NGAP_Enumerated {

    public static final NGAP_EmergencyFallbackRequestIndicator EMERGENCY_FALLBACK_REQUESTED = new NGAP_EmergencyFallbackRequestIndicator("emergency-fallback-requested");

    protected NGAP_EmergencyFallbackRequestIndicator(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "EmergencyFallbackRequestIndicator";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyFallbackRequestIndicator";
    }
}

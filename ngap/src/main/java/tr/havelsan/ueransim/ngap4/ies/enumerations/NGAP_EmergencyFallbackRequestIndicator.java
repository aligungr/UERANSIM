package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_EmergencyFallbackRequestIndicator extends NgapEnumerated {

    public static final NGAP_EmergencyFallbackRequestIndicator EMERGENCY_FALLBACK_REQUESTED = new NGAP_EmergencyFallbackRequestIndicator("emergency-fallback-requested");

    protected NGAP_EmergencyFallbackRequestIndicator(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "EmergencyFallbackRequestIndicator";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyFallbackRequestIndicator";
    }
}

package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_DirectForwardingPathAvailability extends NgapEnumerated {

    public static final NGAP_DirectForwardingPathAvailability DIRECT_PATH_AVAILABLE = new NGAP_DirectForwardingPathAvailability("direct-path-available");

    protected NGAP_DirectForwardingPathAvailability(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DirectForwardingPathAvailability";
    }

    @Override
    protected String getXmlTagName() {
        return "DirectForwardingPathAvailability";
    }
}

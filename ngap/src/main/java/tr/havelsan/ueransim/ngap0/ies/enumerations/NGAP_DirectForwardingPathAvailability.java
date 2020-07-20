package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DirectForwardingPathAvailability extends NGAP_Enumerated {

    public static final NGAP_DirectForwardingPathAvailability DIRECT_PATH_AVAILABLE = new NGAP_DirectForwardingPathAvailability("direct-path-available");

    protected NGAP_DirectForwardingPathAvailability(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DirectForwardingPathAvailability";
    }

    @Override
    public String getXmlTagName() {
        return "DirectForwardingPathAvailability";
    }
}

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_CauseTransport extends NGAP_Enumerated {

    public static final NGAP_CauseTransport TRANSPORT_RESOURCE_UNAVAILABLE = new NGAP_CauseTransport("transport-resource-unavailable");
    public static final NGAP_CauseTransport UNSPECIFIED = new NGAP_CauseTransport("unspecified");

    protected NGAP_CauseTransport(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseTransport";
    }

    @Override
    public String getXmlTagName() {
        return "CauseTransport";
    }
}

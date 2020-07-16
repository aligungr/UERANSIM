package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_CauseTransport extends NgapEnumerated {

    public static final NGAP_CauseTransport TRANSPORT_RESOURCE_UNAVAILABLE = new NGAP_CauseTransport("transport-resource-unavailable");
    public static final NGAP_CauseTransport UNSPECIFIED = new NGAP_CauseTransport("unspecified");

    protected NGAP_CauseTransport(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "CauseTransport";
    }

    @Override
    protected String getXmlTagName() {
        return "CauseTransport";
    }
}

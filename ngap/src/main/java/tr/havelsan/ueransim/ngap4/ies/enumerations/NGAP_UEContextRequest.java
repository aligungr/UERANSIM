package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_UEContextRequest extends NgapEnumerated {

    public static final NGAP_UEContextRequest REQUESTED = new NGAP_UEContextRequest("requested");

    protected NGAP_UEContextRequest(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "UEContextRequest";
    }

    @Override
    protected String getXmlTagName() {
        return "UEContextRequest";
    }
}

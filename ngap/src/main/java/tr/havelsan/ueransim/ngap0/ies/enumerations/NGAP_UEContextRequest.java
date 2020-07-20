package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_UEContextRequest extends NGAP_Enumerated {

    public static final NGAP_UEContextRequest REQUESTED = new NGAP_UEContextRequest("requested");

    protected NGAP_UEContextRequest(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "UEContextRequest";
    }

    @Override
    public String getXmlTagName() {
        return "UEContextRequest";
    }
}

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_SONInformationRequest extends NGAP_Enumerated {

    public static final NGAP_SONInformationRequest XN_TNL_CONFIGURATION_INFO = new NGAP_SONInformationRequest("xn-TNL-configuration-info");

    protected NGAP_SONInformationRequest(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "SONInformationRequest";
    }

    @Override
    public String getXmlTagName() {
        return "SONInformationRequest";
    }
}

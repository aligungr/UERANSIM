package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_SONInformationRequest extends NgapEnumerated {

    public static final NGAP_SONInformationRequest XN_TNL_CONFIGURATION_INFO = new NGAP_SONInformationRequest("xn-TNL-configuration-info");

    protected NGAP_SONInformationRequest(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "SONInformationRequest";
    }

    @Override
    protected String getXmlTagName() {
        return "SONInformationRequest";
    }
}

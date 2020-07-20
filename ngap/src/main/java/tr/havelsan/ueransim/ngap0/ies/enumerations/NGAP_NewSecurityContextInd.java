package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_NewSecurityContextInd extends NGAP_Enumerated {

    public static final NGAP_NewSecurityContextInd TRUE = new NGAP_NewSecurityContextInd("true");

    protected NGAP_NewSecurityContextInd(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NewSecurityContextInd";
    }

    @Override
    public String getXmlTagName() {
        return "NewSecurityContextInd";
    }
}

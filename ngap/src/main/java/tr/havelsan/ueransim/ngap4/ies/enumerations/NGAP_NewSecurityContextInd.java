package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_NewSecurityContextInd extends NgapEnumerated {

    public static final NGAP_NewSecurityContextInd TRUE = new NGAP_NewSecurityContextInd("true");

    protected NGAP_NewSecurityContextInd(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "NewSecurityContextInd";
    }

    @Override
    protected String getXmlTagName() {
        return "NewSecurityContextInd";
    }
}

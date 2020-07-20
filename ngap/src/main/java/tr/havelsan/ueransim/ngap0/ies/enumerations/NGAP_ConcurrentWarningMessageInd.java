package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ConcurrentWarningMessageInd extends NGAP_Enumerated {

    public static final NGAP_ConcurrentWarningMessageInd TRUE = new NGAP_ConcurrentWarningMessageInd("true");

    protected NGAP_ConcurrentWarningMessageInd(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ConcurrentWarningMessageInd";
    }

    @Override
    public String getXmlTagName() {
        return "ConcurrentWarningMessageInd";
    }
}

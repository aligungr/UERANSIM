package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_CancelAllWarningMessages extends NGAP_Enumerated {

    public static final NGAP_CancelAllWarningMessages TRUE = new NGAP_CancelAllWarningMessages("true");

    protected NGAP_CancelAllWarningMessages(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CancelAllWarningMessages";
    }

    @Override
    public String getXmlTagName() {
        return "CancelAllWarningMessages";
    }
}

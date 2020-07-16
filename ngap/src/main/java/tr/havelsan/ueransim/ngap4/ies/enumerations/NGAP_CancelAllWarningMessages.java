package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_CancelAllWarningMessages extends NgapEnumerated {

    public static final NGAP_CancelAllWarningMessages TRUE = new NGAP_CancelAllWarningMessages("true");

    protected NGAP_CancelAllWarningMessages(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "CancelAllWarningMessages";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelAllWarningMessages";
    }
}

package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ConcurrentWarningMessageInd extends NgapEnumerated {

    public static final NGAP_ConcurrentWarningMessageInd TRUE = new NGAP_ConcurrentWarningMessageInd("true");

    protected NGAP_ConcurrentWarningMessageInd(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ConcurrentWarningMessageInd";
    }

    @Override
    protected String getXmlTagName() {
        return "ConcurrentWarningMessageInd";
    }
}

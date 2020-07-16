package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_TypeOfError extends NgapEnumerated {

    public static final NGAP_TypeOfError NOT_UNDERSTOOD = new NGAP_TypeOfError("not-understood");
    public static final NGAP_TypeOfError MISSING = new NGAP_TypeOfError("missing");

    protected NGAP_TypeOfError(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TypeOfError";
    }

    @Override
    protected String getXmlTagName() {
        return "TypeOfError";
    }
}

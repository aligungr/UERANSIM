package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ResetAll extends NgapEnumerated {

    public static final NGAP_ResetAll RESET_ALL = new NGAP_ResetAll("reset-all");

    protected NGAP_ResetAll(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ResetAll";
    }

    @Override
    protected String getXmlTagName() {
        return "ResetAll";
    }
}

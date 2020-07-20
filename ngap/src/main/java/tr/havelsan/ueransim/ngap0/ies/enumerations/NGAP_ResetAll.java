package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ResetAll extends NGAP_Enumerated {

    public static final NGAP_ResetAll RESET_ALL = new NGAP_ResetAll("reset-all");

    protected NGAP_ResetAll(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ResetAll";
    }

    @Override
    public String getXmlTagName() {
        return "ResetAll";
    }
}

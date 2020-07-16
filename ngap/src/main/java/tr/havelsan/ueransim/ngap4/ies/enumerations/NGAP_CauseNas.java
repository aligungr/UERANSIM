package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_CauseNas extends NgapEnumerated {

    public static final NGAP_CauseNas NORMAL_RELEASE = new NGAP_CauseNas("normal-release");
    public static final NGAP_CauseNas AUTHENTICATION_FAILURE = new NGAP_CauseNas("authentication-failure");
    public static final NGAP_CauseNas DEREGISTER = new NGAP_CauseNas("deregister");
    public static final NGAP_CauseNas UNSPECIFIED = new NGAP_CauseNas("unspecified");

    protected NGAP_CauseNas(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "CauseNas";
    }

    @Override
    protected String getXmlTagName() {
        return "CauseNas";
    }
}

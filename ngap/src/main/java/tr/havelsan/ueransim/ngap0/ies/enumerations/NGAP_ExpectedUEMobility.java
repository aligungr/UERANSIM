package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ExpectedUEMobility extends NGAP_Enumerated {

    public static final NGAP_ExpectedUEMobility STATIONARY = new NGAP_ExpectedUEMobility("stationary");
    public static final NGAP_ExpectedUEMobility MOBILE = new NGAP_ExpectedUEMobility("mobile");

    protected NGAP_ExpectedUEMobility(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ExpectedUEMobility";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedUEMobility";
    }
}

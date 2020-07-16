package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ExpectedUEMobility extends NgapEnumerated {

    public static final NGAP_ExpectedUEMobility STATIONARY = new NGAP_ExpectedUEMobility("stationary");
    public static final NGAP_ExpectedUEMobility MOBILE = new NGAP_ExpectedUEMobility("mobile");

    protected NGAP_ExpectedUEMobility(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ExpectedUEMobility";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedUEMobility";
    }
}

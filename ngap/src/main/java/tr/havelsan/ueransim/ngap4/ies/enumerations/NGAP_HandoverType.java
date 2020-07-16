package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_HandoverType extends NgapEnumerated {

    public static final NGAP_HandoverType INTRA5GS = new NGAP_HandoverType("intra5gs");
    public static final NGAP_HandoverType FIVEGS_TO_EPS = new NGAP_HandoverType("fivegs-to-eps");
    public static final NGAP_HandoverType EPS_TO_5GS = new NGAP_HandoverType("eps-to-5gs");

    protected NGAP_HandoverType(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "HandoverType";
    }

    @Override
    protected String getXmlTagName() {
        return "HandoverType";
    }
}

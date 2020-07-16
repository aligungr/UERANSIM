package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ConfidentialityProtectionResult extends NgapEnumerated {

    public static final NGAP_ConfidentialityProtectionResult PERFORMED = new NGAP_ConfidentialityProtectionResult("performed");
    public static final NGAP_ConfidentialityProtectionResult NOT_PERFORMED = new NGAP_ConfidentialityProtectionResult("not-performed");

    protected NGAP_ConfidentialityProtectionResult(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ConfidentialityProtectionResult";
    }

    @Override
    protected String getXmlTagName() {
        return "ConfidentialityProtectionResult";
    }
}

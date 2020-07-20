package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ConfidentialityProtectionResult extends NGAP_Enumerated {

    public static final NGAP_ConfidentialityProtectionResult PERFORMED = new NGAP_ConfidentialityProtectionResult("performed");
    public static final NGAP_ConfidentialityProtectionResult NOT_PERFORMED = new NGAP_ConfidentialityProtectionResult("not-performed");

    protected NGAP_ConfidentialityProtectionResult(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ConfidentialityProtectionResult";
    }

    @Override
    public String getXmlTagName() {
        return "ConfidentialityProtectionResult";
    }
}

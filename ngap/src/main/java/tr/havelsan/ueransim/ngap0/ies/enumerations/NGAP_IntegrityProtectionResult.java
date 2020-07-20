package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_IntegrityProtectionResult extends NGAP_Enumerated {

    public static final NGAP_IntegrityProtectionResult PERFORMED = new NGAP_IntegrityProtectionResult("performed");
    public static final NGAP_IntegrityProtectionResult NOT_PERFORMED = new NGAP_IntegrityProtectionResult("not-performed");

    protected NGAP_IntegrityProtectionResult(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "IntegrityProtectionResult";
    }

    @Override
    public String getXmlTagName() {
        return "IntegrityProtectionResult";
    }
}

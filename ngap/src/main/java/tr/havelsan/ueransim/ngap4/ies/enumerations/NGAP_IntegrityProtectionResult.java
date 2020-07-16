package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_IntegrityProtectionResult extends NgapEnumerated {

    public static final NGAP_IntegrityProtectionResult PERFORMED = new NGAP_IntegrityProtectionResult("performed");
    public static final NGAP_IntegrityProtectionResult NOT_PERFORMED = new NGAP_IntegrityProtectionResult("not-performed");

    protected NGAP_IntegrityProtectionResult(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "IntegrityProtectionResult";
    }

    @Override
    protected String getXmlTagName() {
        return "IntegrityProtectionResult";
    }
}

package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_IntegrityProtectionIndication extends NgapEnumerated {

    public static final NGAP_IntegrityProtectionIndication REQUIRED = new NGAP_IntegrityProtectionIndication("required");
    public static final NGAP_IntegrityProtectionIndication PREFERRED = new NGAP_IntegrityProtectionIndication("preferred");
    public static final NGAP_IntegrityProtectionIndication NOT_NEEDED = new NGAP_IntegrityProtectionIndication("not-needed");

    protected NGAP_IntegrityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "IntegrityProtectionIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "IntegrityProtectionIndication";
    }
}

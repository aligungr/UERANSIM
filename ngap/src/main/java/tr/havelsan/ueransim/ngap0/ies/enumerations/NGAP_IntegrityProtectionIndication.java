package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_IntegrityProtectionIndication extends NGAP_Enumerated {

    public static final NGAP_IntegrityProtectionIndication REQUIRED = new NGAP_IntegrityProtectionIndication("required");
    public static final NGAP_IntegrityProtectionIndication PREFERRED = new NGAP_IntegrityProtectionIndication("preferred");
    public static final NGAP_IntegrityProtectionIndication NOT_NEEDED = new NGAP_IntegrityProtectionIndication("not-needed");

    protected NGAP_IntegrityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "IntegrityProtectionIndication";
    }

    @Override
    public String getXmlTagName() {
        return "IntegrityProtectionIndication";
    }
}

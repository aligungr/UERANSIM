package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ConfidentialityProtectionIndication extends NgapEnumerated {

    public static final NGAP_ConfidentialityProtectionIndication REQUIRED = new NGAP_ConfidentialityProtectionIndication("required");
    public static final NGAP_ConfidentialityProtectionIndication PREFERRED = new NGAP_ConfidentialityProtectionIndication("preferred");
    public static final NGAP_ConfidentialityProtectionIndication NOT_NEEDED = new NGAP_ConfidentialityProtectionIndication("not-needed");

    protected NGAP_ConfidentialityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ConfidentialityProtectionIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "ConfidentialityProtectionIndication";
    }
}

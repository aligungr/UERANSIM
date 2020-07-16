package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ConfidentialityProtectionIndication;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_IntegrityProtectionIndication;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_MaximumIntegrityProtectedDataRate;

public class NGAP_SecurityIndication extends NgapSequence {

    public NGAP_IntegrityProtectionIndication integrityProtectionIndication;
    public NGAP_ConfidentialityProtectionIndication confidentialityProtectionIndication;
    public NGAP_MaximumIntegrityProtectedDataRate maximumIntegrityProtectedDataRate;

    @Override
    protected String getAsnName() {
        return "SecurityIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "SecurityIndication";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate"};
    }
}

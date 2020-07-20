package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_SecurityIndication extends NGAP_Sequence {

    public NGAP_IntegrityProtectionIndication integrityProtectionIndication;
    public NGAP_ConfidentialityProtectionIndication confidentialityProtectionIndication;
    public NGAP_MaximumIntegrityProtectedDataRate maximumIntegrityProtectedDataRate;

    @Override
    public String getAsnName() {
        return "SecurityIndication";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityIndication";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate"};
    }
}

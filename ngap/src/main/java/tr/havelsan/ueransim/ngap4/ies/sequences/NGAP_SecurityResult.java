package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ConfidentialityProtectionResult;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_IntegrityProtectionResult;

public class NGAP_SecurityResult extends NgapSequence {

    public NGAP_IntegrityProtectionResult integrityProtectionResult;
    public NGAP_ConfidentialityProtectionResult confidentialityProtectionResult;

    @Override
    protected String getAsnName() {
        return "SecurityResult";
    }

    @Override
    protected String getXmlTagName() {
        return "SecurityResult";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"integrityProtectionResult", "confidentialityProtectionResult"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"integrityProtectionResult", "confidentialityProtectionResult"};
    }
}

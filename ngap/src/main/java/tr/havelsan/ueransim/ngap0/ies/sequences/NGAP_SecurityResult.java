package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_SecurityResult extends NGAP_Sequence {

    public NGAP_IntegrityProtectionResult integrityProtectionResult;
    public NGAP_ConfidentialityProtectionResult confidentialityProtectionResult;

    @Override
    public String getAsnName() {
        return "SecurityResult";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityResult";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"integrityProtectionResult", "confidentialityProtectionResult"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"integrityProtectionResult", "confidentialityProtectionResult"};
    }
}

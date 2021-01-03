/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_ConfidentialityProtectionIndication;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_IntegrityProtectionIndication;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_MaximumIntegrityProtectedDataRate;

public class NGAP_SecurityIndication extends NGAP_Sequence {

    public NGAP_IntegrityProtectionIndication integrityProtectionIndication;
    public NGAP_ConfidentialityProtectionIndication confidentialityProtectionIndication;
    public NGAP_MaximumIntegrityProtectedDataRate maximumIntegrityProtectedDataRate_UL;

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
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate-UL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"integrityProtectionIndication", "confidentialityProtectionIndication", "maximumIntegrityProtectedDataRate_UL"};
    }
}

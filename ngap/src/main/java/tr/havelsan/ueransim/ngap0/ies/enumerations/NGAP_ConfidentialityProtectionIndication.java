/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_ConfidentialityProtectionIndication extends NGAP_Enumerated {

    public static final NGAP_ConfidentialityProtectionIndication REQUIRED = new NGAP_ConfidentialityProtectionIndication("required");
    public static final NGAP_ConfidentialityProtectionIndication PREFERRED = new NGAP_ConfidentialityProtectionIndication("preferred");
    public static final NGAP_ConfidentialityProtectionIndication NOT_NEEDED = new NGAP_ConfidentialityProtectionIndication("not-needed");

    protected NGAP_ConfidentialityProtectionIndication(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ConfidentialityProtectionIndication";
    }

    @Override
    public String getXmlTagName() {
        return "ConfidentialityProtectionIndication";
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

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

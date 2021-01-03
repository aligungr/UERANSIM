/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_UEPresence extends NGAP_Enumerated {

    public static final NGAP_UEPresence IN = new NGAP_UEPresence("in");
    public static final NGAP_UEPresence OUT = new NGAP_UEPresence("out");
    public static final NGAP_UEPresence UNKNOWN = new NGAP_UEPresence("unknown");

    protected NGAP_UEPresence(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "UEPresence";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresence";
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_Presence extends NGAP_Enumerated {

    public static final NGAP_Presence OPTIONAL = new NGAP_Presence("optional");
    public static final NGAP_Presence CONDITIONAL = new NGAP_Presence("conditional");
    public static final NGAP_Presence MANDATORY = new NGAP_Presence("mandatory");

    protected NGAP_Presence(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "Presence";
    }

    @Override
    public String getXmlTagName() {
        return "Presence";
    }
}

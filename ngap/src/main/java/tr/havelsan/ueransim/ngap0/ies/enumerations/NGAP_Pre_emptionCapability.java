/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_Pre_emptionCapability extends NGAP_Enumerated {

    public static final NGAP_Pre_emptionCapability SHALL_NOT_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("shall-not-trigger-pre-emption");
    public static final NGAP_Pre_emptionCapability MAY_TRIGGER_PRE_EMPTION = new NGAP_Pre_emptionCapability("may-trigger-pre-emption");

    protected NGAP_Pre_emptionCapability(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "Pre-emptionCapability";
    }

    @Override
    public String getXmlTagName() {
        return "Pre-emptionCapability";
    }
}

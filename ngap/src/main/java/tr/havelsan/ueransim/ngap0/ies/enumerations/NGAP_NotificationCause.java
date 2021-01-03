/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_NotificationCause extends NGAP_Enumerated {

    public static final NGAP_NotificationCause FULFILLED = new NGAP_NotificationCause("fulfilled");
    public static final NGAP_NotificationCause NOT_FULFILLED = new NGAP_NotificationCause("not-fulfilled");

    protected NGAP_NotificationCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NotificationCause";
    }

    @Override
    public String getXmlTagName() {
        return "NotificationCause";
    }
}

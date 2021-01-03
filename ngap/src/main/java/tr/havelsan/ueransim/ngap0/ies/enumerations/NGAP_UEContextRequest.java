/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_UEContextRequest extends NGAP_Enumerated {

    public static final NGAP_UEContextRequest REQUESTED = new NGAP_UEContextRequest("requested");

    protected NGAP_UEContextRequest(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "UEContextRequest";
    }

    @Override
    public String getXmlTagName() {
        return "UEContextRequest";
    }
}

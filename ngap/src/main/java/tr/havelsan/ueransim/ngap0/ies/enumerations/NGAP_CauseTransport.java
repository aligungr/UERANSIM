/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_CauseTransport extends NGAP_Enumerated {

    public static final NGAP_CauseTransport TRANSPORT_RESOURCE_UNAVAILABLE = new NGAP_CauseTransport("transport-resource-unavailable");
    public static final NGAP_CauseTransport UNSPECIFIED = new NGAP_CauseTransport("unspecified");

    protected NGAP_CauseTransport(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseTransport";
    }

    @Override
    public String getXmlTagName() {
        return "CauseTransport";
    }
}

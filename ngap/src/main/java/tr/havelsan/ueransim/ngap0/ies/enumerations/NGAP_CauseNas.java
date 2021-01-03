/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_CauseNas extends NGAP_Enumerated {

    public static final NGAP_CauseNas NORMAL_RELEASE = new NGAP_CauseNas("normal-release");
    public static final NGAP_CauseNas AUTHENTICATION_FAILURE = new NGAP_CauseNas("authentication-failure");
    public static final NGAP_CauseNas DEREGISTER = new NGAP_CauseNas("deregister");
    public static final NGAP_CauseNas UNSPECIFIED = new NGAP_CauseNas("unspecified");

    protected NGAP_CauseNas(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseNas";
    }

    @Override
    public String getXmlTagName() {
        return "CauseNas";
    }
}

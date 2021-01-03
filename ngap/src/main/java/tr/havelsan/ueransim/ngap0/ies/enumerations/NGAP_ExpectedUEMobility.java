/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_ExpectedUEMobility extends NGAP_Enumerated {

    public static final NGAP_ExpectedUEMobility STATIONARY = new NGAP_ExpectedUEMobility("stationary");
    public static final NGAP_ExpectedUEMobility MOBILE = new NGAP_ExpectedUEMobility("mobile");

    protected NGAP_ExpectedUEMobility(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ExpectedUEMobility";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedUEMobility";
    }
}

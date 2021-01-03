/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_ExpectedHOInterval extends NGAP_Enumerated {

    public static final NGAP_ExpectedHOInterval SEC15 = new NGAP_ExpectedHOInterval("sec15");
    public static final NGAP_ExpectedHOInterval SEC30 = new NGAP_ExpectedHOInterval("sec30");
    public static final NGAP_ExpectedHOInterval SEC60 = new NGAP_ExpectedHOInterval("sec60");
    public static final NGAP_ExpectedHOInterval SEC90 = new NGAP_ExpectedHOInterval("sec90");
    public static final NGAP_ExpectedHOInterval SEC120 = new NGAP_ExpectedHOInterval("sec120");
    public static final NGAP_ExpectedHOInterval SEC180 = new NGAP_ExpectedHOInterval("sec180");
    public static final NGAP_ExpectedHOInterval LONG_TIME = new NGAP_ExpectedHOInterval("long-time");

    protected NGAP_ExpectedHOInterval(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ExpectedHOInterval";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedHOInterval";
    }
}

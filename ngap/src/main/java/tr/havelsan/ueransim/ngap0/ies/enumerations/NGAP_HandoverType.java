/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_HandoverType extends NGAP_Enumerated {

    public static final NGAP_HandoverType INTRA5GS = new NGAP_HandoverType("intra5gs");
    public static final NGAP_HandoverType FIVEGS_TO_EPS = new NGAP_HandoverType("fivegs-to-eps");
    public static final NGAP_HandoverType EPS_TO_5GS = new NGAP_HandoverType("eps-to-5gs");

    protected NGAP_HandoverType(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "HandoverType";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverType";
    }
}

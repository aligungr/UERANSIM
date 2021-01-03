/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_CellSize extends NGAP_Enumerated {

    public static final NGAP_CellSize VERYSMALL = new NGAP_CellSize("verysmall");
    public static final NGAP_CellSize SMALL = new NGAP_CellSize("small");
    public static final NGAP_CellSize MEDIUM = new NGAP_CellSize("medium");
    public static final NGAP_CellSize LARGE = new NGAP_CellSize("large");

    protected NGAP_CellSize(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CellSize";
    }

    @Override
    public String getXmlTagName() {
        return "CellSize";
    }
}

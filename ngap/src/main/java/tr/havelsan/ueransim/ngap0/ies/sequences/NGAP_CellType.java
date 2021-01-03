/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CellSize;

public class NGAP_CellType extends NGAP_Sequence {

    public NGAP_CellSize cellSize;

    @Override
    public String getAsnName() {
        return "CellType";
    }

    @Override
    public String getXmlTagName() {
        return "CellType";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"cellSize"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"cellSize"};
    }
}

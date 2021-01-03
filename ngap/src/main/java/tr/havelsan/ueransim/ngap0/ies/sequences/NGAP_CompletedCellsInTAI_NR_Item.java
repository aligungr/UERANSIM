/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_CompletedCellsInTAI_NR_Item extends NGAP_Sequence {

    public NGAP_NR_CGI nR_CGI;

    @Override
    public String getAsnName() {
        return "CompletedCellsInTAI-NR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInTAI-NR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI"};
    }
}

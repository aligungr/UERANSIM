/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_CompletedCellsInTAI_EUTRA_Item extends NGAP_Sequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    public String getAsnName() {
        return "CompletedCellsInTAI-EUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInTAI-EUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI"};
    }
}

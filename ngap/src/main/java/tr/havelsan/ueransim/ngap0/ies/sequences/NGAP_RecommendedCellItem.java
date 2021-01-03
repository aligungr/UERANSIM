/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Integer;
import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_NGRAN_CGI;

public class NGAP_RecommendedCellItem extends NGAP_Sequence {

    public NGAP_NGRAN_CGI nGRAN_CGI;
    public NGAP_Integer timeStayedInCell;

    @Override
    public String getAsnName() {
        return "RecommendedCellItem";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedCellItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRAN-CGI", "timeStayedInCell"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRAN_CGI", "timeStayedInCell"};
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_NGRAN_CGI;

public class NGAP_AreaOfInterestCellItem extends NGAP_Sequence {

    public NGAP_NGRAN_CGI nGRAN_CGI;

    @Override
    public String getAsnName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRAN-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRAN_CGI"};
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_EUTRA_CGIList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_NR_CGIList;

public class NGAP_PWSFailedCellIDList extends NGAP_Choice {

    public NGAP_EUTRA_CGIList eUTRA_CGI_PWSFailedList;
    public NGAP_NR_CGIList nR_CGI_PWSFailedList;

    @Override
    public String getAsnName() {
        return "PWSFailedCellIDList";
    }

    @Override
    public String getXmlTagName() {
        return "PWSFailedCellIDList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI-PWSFailedList", "nR-CGI-PWSFailedList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI_PWSFailedList", "nR_CGI_PWSFailedList"};
    }
}

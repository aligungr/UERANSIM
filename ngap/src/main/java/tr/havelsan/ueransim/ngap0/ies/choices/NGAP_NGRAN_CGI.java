/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_EUTRA_CGI;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_NR_CGI;

public class NGAP_NGRAN_CGI extends NGAP_Choice {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    public String getAsnName() {
        return "NGRAN-CGI";
    }

    @Override
    public String getXmlTagName() {
        return "NGRAN-CGI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI", "eUTRA-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "eUTRA_CGI"};
    }
}

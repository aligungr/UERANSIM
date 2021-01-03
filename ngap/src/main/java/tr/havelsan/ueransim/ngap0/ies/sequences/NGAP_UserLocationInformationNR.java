/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_TimeStamp;

public class NGAP_UserLocationInformationNR extends NGAP_Sequence {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_TAI tAI;
    public NGAP_TimeStamp timeStamp;

    @Override
    public String getAsnName() {
        return "UserLocationInformationNR";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformationNR";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI", "tAI", "timeStamp"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "tAI", "timeStamp"};
    }
}

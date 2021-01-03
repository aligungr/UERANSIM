/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_FiveG_S_TMSI;

public class NGAP_UEPagingIdentity extends NGAP_Choice {

    public NGAP_FiveG_S_TMSI fiveG_S_TMSI;

    @Override
    public String getAsnName() {
        return "UEPagingIdentity";
    }

    @Override
    public String getXmlTagName() {
        return "UEPagingIdentity";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"fiveG-S-TMSI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"fiveG_S_TMSI"};
    }
}

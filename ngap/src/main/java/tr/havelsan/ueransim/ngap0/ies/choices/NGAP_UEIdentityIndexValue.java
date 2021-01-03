/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_BitString;
import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;

public class NGAP_UEIdentityIndexValue extends NGAP_Choice {

    public NGAP_BitString indexLength10;

    @Override
    public String getAsnName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String getXmlTagName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"indexLength10"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"indexLength10"};
    }
}

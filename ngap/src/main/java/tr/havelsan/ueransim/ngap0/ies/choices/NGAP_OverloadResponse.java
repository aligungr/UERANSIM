/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_OverloadAction;

public class NGAP_OverloadResponse extends NGAP_Choice {

    public NGAP_OverloadAction overloadAction;

    @Override
    public String getAsnName() {
        return "OverloadResponse";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadResponse";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"overloadAction"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"overloadAction"};
    }
}

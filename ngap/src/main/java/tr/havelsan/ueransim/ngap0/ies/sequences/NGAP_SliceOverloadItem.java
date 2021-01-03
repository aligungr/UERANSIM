/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_SliceOverloadItem extends NGAP_Sequence {

    public NGAP_S_NSSAI s_NSSAI;

    @Override
    public String getAsnName() {
        return "SliceOverloadItem";
    }

    @Override
    public String getXmlTagName() {
        return "SliceOverloadItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"s-NSSAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"s_NSSAI"};
    }
}

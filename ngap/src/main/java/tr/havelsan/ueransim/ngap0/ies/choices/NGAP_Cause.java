/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_Cause extends NGAP_Choice {

    public NGAP_CauseRadioNetwork radioNetwork;
    public NGAP_CauseTransport transport;
    public NGAP_CauseNas nas;
    public NGAP_CauseProtocol protocol;
    public NGAP_CauseMisc misc;

    @Override
    public String getAsnName() {
        return "Cause";
    }

    @Override
    public String getXmlTagName() {
        return "Cause";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"radioNetwork", "transport", "nas", "protocol", "misc"};
    }
}

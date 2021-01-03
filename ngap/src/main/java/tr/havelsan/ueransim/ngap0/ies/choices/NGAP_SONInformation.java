/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_SONInformationRequest;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SONInformationReply;

public class NGAP_SONInformation extends NGAP_Choice {

    public NGAP_SONInformationRequest sONInformationRequest;
    public NGAP_SONInformationReply sONInformationReply;

    @Override
    public String getAsnName() {
        return "SONInformation";
    }

    @Override
    public String getXmlTagName() {
        return "SONInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }
}

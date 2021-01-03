/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.pdu;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;

public class NGAP_PDU extends NGAP_Choice {

    public NGAP_InitiatingMessage initiatingMessage;
    public NGAP_SuccessfulOutcome successfulOutcome;
    public NGAP_UnsuccessfulOutcome unsuccessfulOutcome;

    @Override
    public String[] getMemberNames() {
        return new String[]{"initiatingMessage", "successfulOutcome", "unsuccessfulOutcome"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"initiatingMessage", "successfulOutcome", "unsuccessfulOutcome"};
    }

    @Override
    public String getAsnName() {
        return "NGAP-PDU";
    }

    @Override
    public String getXmlTagName() {
        return "NGAP-PDU";
    }
}

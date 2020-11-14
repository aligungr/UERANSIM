/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

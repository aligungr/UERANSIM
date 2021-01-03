/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0;

import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_MessageChoice;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;

public class Ngap {

    public static NGAP_BaseMessage getMessageFromPdu(NGAP_PDU pdu) {
        NGAP_MessageChoice choice;
        if (pdu.initiatingMessage != null) {
            choice = pdu.initiatingMessage.value;
        } else if (pdu.successfulOutcome != null) {
            choice = pdu.successfulOutcome.value;
        } else if (pdu.unsuccessfulOutcome != null) {
            choice = pdu.unsuccessfulOutcome.value;
        } else {
            return null;
        }
        return (NGAP_BaseMessage) choice.getPresentValue();
    }

    public static <T extends NGAP_Value> T deepClone(T value) {
        return (T) NgapXerEncoder.decode(NgapXerEncoder.encode(value), value.getClass());
    }

}

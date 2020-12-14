/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc;

import tr.havelsan.ueransim.app.l2.rlc.entity.AmEntity;
import tr.havelsan.ueransim.app.l2.rlc.entity.TmEntity;
import tr.havelsan.ueransim.app.l2.rlc.entity.UmEntity;
import tr.havelsan.ueransim.app.l2.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.l2.rlc.pdu.TmdPdu;
import tr.havelsan.ueransim.app.l2.rlc.pdu.UmdPdu;

public class RlcDataTransfer {

    public static void transmitTmd(TmEntity entity, TmdPdu pdu) {
        RlcTransfer.sendPdu(entity, pdu.data);
    }

    public static void receiveTmd(TmEntity entity, TmdPdu pdu) {
        RlcTransfer.deliverSdu(entity, pdu.data);
    }

    public static void transmitUmd(UmEntity entity, UmdPdu pdu) {

    }

    public static void receiveUmd(UmEntity entity, UmdPdu pdu) {

    }

    public static void transmitAmd(AmEntity entity, AmdPdu pdu) {

    }

    public static void receiveAmd(AmEntity entity, AmdPdu pdu) {

    }
}

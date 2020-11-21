/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.sm;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_PduSessionEstablishment;
import tr.havelsan.ueransim.app.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.app.ue.nas.NasTimer;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEPayloadContainer;
import tr.havelsan.ueransim.nas.impl.ies.IEPayloadContainerType;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionIdentity2;
import tr.havelsan.ueransim.nas.impl.ies.IERequestType;
import tr.havelsan.ueransim.nas.impl.messages.DlNasTransport;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentAccept;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentReject;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class SessionManagement {

    public static void sendSm(UeSimContext ctx, EPduSessionIdentity psi, PlainSmMessage message) {
        // TODO
        var ulNasTransport = new UlNasTransport();
        ulNasTransport.payloadContainerType = new IEPayloadContainerType(IEPayloadContainerType.EPayloadContainerType.N1_SM_INFORMATION);
        ulNasTransport.payloadContainer = new IEPayloadContainer(NasEncoder.nasPduS(message));
        ulNasTransport.pduSessionId = new IEPduSessionIdentity2(psi.intValue());
        ulNasTransport.requestType = new IERequestType(IERequestType.ERequestType.INITIAL_REQUEST);
        ulNasTransport.sNssa = ctx.ueConfig.requestedNssai[0];
        ulNasTransport.dnn = ctx.ueConfig.dnn;

        MobilityManagement.sendMm(ctx, ulNasTransport);
    }

    public static void receiveDl(UeSimContext ctx, DlNasTransport message) {
        receiveSm(ctx, (PlainSmMessage) NasDecoder.nasPdu(message.payloadContainer.payload));
    }

    public static void receiveSm(UeSimContext ctx, PlainSmMessage message) {
        if (message instanceof PduSessionEstablishmentAccept) {
            SmPduSessionEstablishment.receiveEstablishmentAccept(ctx, (PduSessionEstablishmentAccept) message);
        } else if (message instanceof PduSessionEstablishmentReject) {
            SmPduSessionEstablishment.receiveEstablishmentReject(ctx, (PduSessionEstablishmentReject) message);
        } else {
            Log.error(Tag.MSG, "Unhandled message received: %s", message.getClass().getSimpleName());
        }
    }

    public static void receiveTimerExpire(UeSimContext ctx, NasTimer timer) {
        // todo
    }

    public static boolean executeCommand(UeSimContext ctx, TestCmd cmd) {
        if (cmd instanceof TestCmd_PduSessionEstablishment) {
            SmPduSessionEstablishment.sendEstablishmentRequest(ctx);
            return true;
        }

        return false;
    }
}

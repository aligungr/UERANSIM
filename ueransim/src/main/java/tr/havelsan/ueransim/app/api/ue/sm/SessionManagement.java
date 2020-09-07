/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.api.ue.sm;

import tr.havelsan.ueransim.app.api.nas.NasTimer;
import tr.havelsan.ueransim.app.api.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.testing.TestCommand;
import tr.havelsan.ueransim.app.testing.TestCommand_PduSessionEstablishment;
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
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.utils.console.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class SessionManagement {

    public static void sendSm(UeSimContext ctx, EPduSessionIdentity psi, PlainSmMessage message) {
        Debugging.assertThread(ctx);

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
        Debugging.assertThread(ctx);

        receiveSm(ctx, (PlainSmMessage) NasDecoder.nasPdu(message.payloadContainer.payload));
    }

    public static void receiveSm(UeSimContext ctx, PlainSmMessage message) {
        Debugging.assertThread(ctx);

        if (message instanceof PduSessionEstablishmentAccept) {
            SmPduSessionEstablishment.receiveEstablishmentAccept(ctx, (PduSessionEstablishmentAccept) message);
        } else if (message instanceof PduSessionEstablishmentReject) {
            SmPduSessionEstablishment.receiveEstablishmentReject(ctx, (PduSessionEstablishmentReject) message);
        } else {
            Logging.error(Tag.MESSAGING, "Unhandled message received: %s", message.getClass().getSimpleName());
        }
    }

    public static void receiveTimerExpire(UeSimContext ctx, NasTimer timer) {
        Debugging.assertThread(ctx);

        // todo
    }

    public static boolean executeCommand(UeSimContext ctx, TestCommand cmd) {
        Debugging.assertThread(ctx);

        if (cmd instanceof TestCommand_PduSessionEstablishment) {
            SmPduSessionEstablishment.sendEstablishmentRequest(ctx);
            return true;
        }

        return false;
    }
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.UeConnectionInfo;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.info.UeStatusInfo;
import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

public class UeAppTask extends NtsTask {

    private final UeSimContext ctx;

    private final PingApp pingApp;
    private final UeConnectionInfo connectionInfo;

    private final UeStatusInfo statusInfo;

    private NtsTask nasTask;

    public UeAppTask(UeSimContext ctx) {
        this.ctx = ctx;
        this.connectionInfo = new UeConnectionInfo();
        this.pingApp = new PingApp(ctx, connectionInfo);
        this.statusInfo = new UeStatusInfo();
    }

    @Override
    public void main() {
        nasTask = ctx.nts.findTask(ItmsId.UE_TASK_NAS);

        while (true) {
            var msg = poll(1000);
            pingApp.timeoutControl();

            if (msg instanceof IwUeTestCommand) {
                var cmd = ((IwUeTestCommand) msg).cmd;

                if (cmd instanceof TestCmd_InitialRegistration) {
                    nasTask.push(msg);
                } else if (cmd instanceof TestCmd_PeriodicRegistration) {
                    nasTask.push(msg);
                } else if (cmd instanceof TestCmd_PduSessionEstablishment) {
                    nasTask.push(msg);
                } else if (cmd instanceof TestCmd_Deregistration) {
                    nasTask.push(msg);
                } else if (cmd instanceof TestCmd_Ping) {
                    pingApp.sendPing((TestCmd_Ping) cmd);
                }
            } else if (msg instanceof IwUeConnectionSetup) {
                connectionSetup((IwUeConnectionSetup) msg);
            } else if (msg instanceof IwDownlinkData) {
                pingApp.handlePacket(((IwDownlinkData) msg).ipPacket);
            } else if (msg instanceof IwUeStatusInfoRequest) {
                var requester = ((IwUeStatusInfoRequest) msg).requester;
                var consumer = ((IwUeStatusInfoRequest) msg).consumerFunc;
                requester.push(() -> consumer.accept(createStatusInfo()));
            } else if (msg instanceof IwUeStatusUpdate) {
                receiveStatusUpdate((IwUeStatusUpdate) msg);
            }
        }
    }

    private void connectionSetup(IwUeConnectionSetup msg) {
        var pduSession = msg.pduSession;
        if (!pduSession.sessionType.pduSessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UEAPP, "Connection could not setup (unsupported PDU Session type: %s)", pduSession.sessionType.pduSessionType);
            return;
        }

        connectionInfo.pduSessionId = pduSession.id.intValue();
        connectionInfo.sessionType = pduSession.pduAddress.sessionType;
        connectionInfo.pduAddress = pduSession.pduAddress.pduAddressInformation.toByteArray();
        connectionInfo.isEstablished = true;

        Log.info(Tag.UEAPP, "%s connection setup with local IP: %s", connectionInfo.sessionType, Utils.byteArrayToIpString(connectionInfo.pduAddress));

        ctx.sim.triggerOnConnected(ctx, EConnType.ANY_IPv4);
    }

    private void receiveStatusUpdate(IwUeStatusUpdate msg) {
        switch (msg.what) {
            case IwUeStatusUpdate.CONNECTED_GNB:
                if (msg.gnbName != null) {
                    statusInfo.connectedGnb = msg.gnbName;
                    statusInfo.isConnected = true;
                } else {
                    statusInfo.connectedGnb = null;
                    statusInfo.isConnected = false;
                }
                break;
            case IwUeStatusUpdate.RM_STATE:
                statusInfo.rmState = msg.rmState.toString();
                break;
            case IwUeStatusUpdate.MM_STATE:
                statusInfo.mmState = msg.mmSubState.toString();
                break;
        }
    }

    private UeStatusInfo createStatusInfo() {
        var inf = new UeStatusInfo();
        inf.isConnected = statusInfo.isConnected;
        inf.connectedGnb = statusInfo.connectedGnb;
        inf.mmState = statusInfo.mmState;
        inf.rmState = statusInfo.rmState;
        return inf;
    }
}

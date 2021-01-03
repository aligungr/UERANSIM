/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.PduSession;
import tr.havelsan.ueransim.app.common.cli.CmdSessionCreate;
import tr.havelsan.ueransim.app.common.cli.CmdUeDeRegistration;
import tr.havelsan.ueransim.app.common.cli.CmdUePing;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.info.UeConnectionInfo;
import tr.havelsan.ueransim.app.common.info.UePduSessionInfo;
import tr.havelsan.ueransim.app.common.info.UeStatusInfo;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.utils.Native;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.LinkedHashMap;

public class UeAppTask extends NtsTask {

    private final UeSimContext ctx;
    private final UeStatusInfo statusInfo;

    private final PingApp pingApp;
    private final UeConnectionInfo connectionInfo;

    private final TunTask[] tunTasks;

    public UeAppTask(UeSimContext ctx) {
        this.ctx = ctx;
        this.connectionInfo = new UeConnectionInfo();
        this.pingApp = new PingApp(ctx, connectionInfo);
        this.statusInfo = new UeStatusInfo();
        this.tunTasks = new TunTask[16];
    }

    @Override
    protected void main() {
        var nasTask = ctx.nts.findTask(NtsId.UE_TASK_NAS);
        var mrTask = ctx.nts.findTask(NtsId.UE_TASK_MR);

        while (true) {
            var msg = poll(1000);
            pingApp.timeoutControl();

            if (msg instanceof IwUeExternalCommand) {
                var cmd = ((IwUeExternalCommand) msg).cmd;

                if (cmd instanceof CmdSessionCreate) {
                    nasTask.push(msg);
                } else if (cmd instanceof CmdUeDeRegistration) {
                    nasTask.push(msg);
                } else if (cmd instanceof CmdUePing) {
                    pingApp.sendPing((CmdUePing) cmd);
                }
            } else if (msg instanceof IwUplinkData) {
                mrTask.push(msg);
            } else if (msg instanceof IwDownlinkData) {
                var iw = (IwDownlinkData) msg;
                var tunTask = tunTasks[iw.pduSessionId];
                if (tunTask != null) {
                    tunTask.push(msg);
                }

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

    private void connectionSetup(PduSession pduSession) {
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
            case IwUeStatusUpdate.SESSION_ESTABLISHMENT:
                connectionSetup(msg.pduSession);

                var pduSessionInfo = new UePduSessionInfo();
                pduSessionInfo.id = msg.pduSession.id.intValue();
                pduSessionInfo.type = msg.pduSession.sessionType.pduSessionType.name();

                var address = msg.pduSession.pduAddress.pduAddressInformation.toHexString();
                if (msg.pduSession.pduAddress.sessionType == EPduSessionType.IPV4 || msg.pduSession.pduAddress.sessionType == EPduSessionType.IPV6) {
                    address = Utils.byteArrayToIpString(msg.pduSession.pduAddress.pduAddressInformation.toByteArray());
                }
                pduSessionInfo.address = address;

                statusInfo.pduSessions.put(msg.pduSession.id.intValue(), pduSessionInfo);

                setupTunInterface(msg.pduSession);
                break;
        }
    }

    private UeStatusInfo createStatusInfo() {
        var inf = new UeStatusInfo();
        inf.isConnected = statusInfo.isConnected;
        inf.connectedGnb = statusInfo.connectedGnb;
        inf.mmState = statusInfo.mmState;
        inf.rmState = statusInfo.rmState;
        inf.pduSessions = new LinkedHashMap<>();
        for (var entry : statusInfo.pduSessions.entrySet()) {
            inf.pduSessions.put(entry.getKey(), entry.getValue());
        }
        return inf;
    }

    private void setupTunInterface(PduSession pduSession) {
        if (!pduSession.sessionType.pduSessionType.equals(EPduSessionType.IPV4)
                || !pduSession.pduAddress.sessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UEAPP, "Connection could not setup (unsupported PDU Session type: %s)", pduSession.sessionType.pduSessionType);
            return;
        }

        if (!Native.isRoot()) {
            Log.error(Tag.UEAPP, "TUN interface could not be setup. Permission denied.");
            return;
        }

        var psi = pduSession.id.intValue();

        if (tunTasks[psi] != null) {
            Log.error(Tag.UEAPP, "TUN task for specified PSI is non-null.");
            return;
        }

        var ipAddress = Utils.byteArrayToIpString(pduSession.pduAddress.pduAddressInformation.toByteArray());

        var error = new String[1];
        var allocatedName = new String[1];

        int fd = Native.tunAllocate("uesimtun", allocatedName, error);
        if (error[0] != null) {
            Log.error(Tag.UEAPP, "TUN allocate failure: %s", error[0]);
            return;
        }

        Native.tunConfigure(allocatedName[0], ipAddress, !Constants.NO_ROUTE_CONFIG, error);
        if (error[0] != null) {
            Log.error(Tag.UEAPP, "TUN configure failure: %s", error[0]);
            return;
        }

        var task = new TunTask(this, ctx.ctxId, psi, fd);
        tunTasks[psi] = task;
        Log.registerLogger(task.getThread(), Log.getLoggerOrDefault(getThread()));
        task.start();
    }
}

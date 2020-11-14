/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.UeConnectionInfo;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwUeConnectionSetup;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

public class UeAppTask extends ItmsTask {

    private final UeSimContext ctx;
    private final PingApp pingApp;
    private final UeConnectionInfo connectionInfo;

    public UeAppTask(Itms itms, int taskId, UeSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
        this.connectionInfo = new UeConnectionInfo();
        this.pingApp = new PingApp(ctx, connectionInfo);
    }

    @Override
    public void main() {
        while (true) {
            var msg = ctx.itms.receiveMessage(this, 1000);
            pingApp.timeoutControl();

            if (msg instanceof IwUeTestCommand) {
                var cmd = ((IwUeTestCommand) msg).cmd;

                if (cmd instanceof TestCmd_InitialRegistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_PeriodicRegistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_PduSessionEstablishment) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_Deregistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_Ping) {
                    pingApp.sendPing((TestCmd_Ping) cmd);
                }
            } else if (msg instanceof IwUeConnectionSetup) {
                connectionSetup((IwUeConnectionSetup) msg);
            } else if (msg instanceof IwDownlinkData) {
                pingApp.handlePacket(((IwDownlinkData) msg).ipPacket);
            }
        }
    }

    private void connectionSetup(IwUeConnectionSetup msg) {
        var pduSession = msg.pduSession;
        if (!pduSession.sessionType.pduSessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UE_APP, "Connection could not setup (unsupported PDU Session type: %s)", pduSession.sessionType.pduSessionType);
            return;
        }

        connectionInfo.pduSessionId = pduSession.id.intValue();
        connectionInfo.sessionType = pduSession.pduAddress.sessionType;
        connectionInfo.pduAddress = pduSession.pduAddress.pduAddressInformation.toByteArray();
        connectionInfo.isEstablished = true;

        Log.info(Tag.UE_APP, "%s connection setup with local IP: %s", connectionInfo.sessionType, Utils.byteArrayToIpString(connectionInfo.pduAddress));
    }
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.cli.CmdEcho;
import tr.havelsan.ueransim.app.common.cli.CmdErrorIndication;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.app.common.itms.IwCliClientMessage;
import tr.havelsan.ueransim.app.common.itms.IwCliServerMessage;
import tr.havelsan.ueransim.itms.nts.NtsTask;

import java.util.UUID;

public class CliTask extends NtsTask {

    private ServerTask serverTask;

    @Override
    public void main() {
        serverTask = new ServerTask(this);
        serverTask.start();

        while (true) {
            var msg = take();

            if (msg instanceof IwCliClientMessage) {
                var client = ((IwCliClientMessage) msg).client;
                var data = ((IwCliClientMessage) msg).data;

                var cmd = CliUtils.decodeCmdPdu(data, s -> sendCmd(client, new CmdErrorIndication(s)));
                receiveCmd(client, cmd);
            }
        }
    }

    private void sendCmd(UUID client, CmdMessage message) {
        serverTask.push(new IwCliServerMessage(client, CliUtils.encodeCmdPdu(message)));
    }

    private void receiveCmd(UUID client, CmdMessage message) {
        if (message instanceof CmdEcho) {
            sendCmd(client, message);
        }
    }
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.itms.IwCliClientMessage;
import tr.havelsan.ueransim.app.common.itms.IwCliServerMessage;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class CliTask extends NtsTask {

    private ServerTask serverTask;

    @Override
    public void main() {
        serverTask = new ServerTask(this);
        serverTask.start();

        while (true) {
            var msg = take();

            if (msg instanceof IwCliClientMessage) {
                // todo just echoing
                serverTask.push(new IwCliServerMessage(((IwCliClientMessage) msg).client, ((IwCliClientMessage) msg).data));
            }

        }
    }
}

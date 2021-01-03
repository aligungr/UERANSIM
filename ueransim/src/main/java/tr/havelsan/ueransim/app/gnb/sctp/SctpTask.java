/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.sctp;

import tr.havelsan.ueransim.app.common.contexts.SctpContext;
import tr.havelsan.ueransim.app.common.nts.IwNgapReceive;
import tr.havelsan.ueransim.app.common.nts.IwSctpAssociationSetup;
import tr.havelsan.ueransim.app.common.nts.IwSctpConnectionRequest;
import tr.havelsan.ueransim.app.common.nts.IwSctpSend;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.sctp.ISctpAssociationHandler;
import tr.havelsan.ueransim.sctp.SctpAssociation;
import tr.havelsan.ueransim.sctp.SctpClient;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class SctpTask extends NtsTask {

    public static final int NGAP_PROTOCOL_ID = 60;

    private final SctpContext ctx;

    public SctpTask(GnbSimContext ctx) {
        this.ctx = new SctpContext(ctx);
    }

    @Override
    protected void main() {
        ctx.ngapTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_NGAP);

        while (true) {
            var msg = take();
            if (msg instanceof IwSctpConnectionRequest) {
                receiveSctpConnectionRequest((IwSctpConnectionRequest) msg);
            } else if (msg instanceof IwSctpSend) {
                receiveSctpSendRequest((IwSctpSend) msg);
            }
        }
    }

    private void receiveSctpConnectionRequest(IwSctpConnectionRequest msg) {

        var associationHandler = new ISctpAssociationHandler() {

            public void onSetup(SctpAssociation sctpAssociation) {
                push(() -> ctx.ngapTask.push(new IwSctpAssociationSetup(msg.clientId, sctpAssociation)));
            }

            public void onShutdown() {

            }
        };

        var client = new SctpClient(ctx.gnbCtx.config.ngapIp, msg.address, msg.port, NGAP_PROTOCOL_ID, associationHandler);
        ctx.clients.put(msg.clientId, client);

        var receiverThread = new Thread(() -> {
            try {
                client.start();
            } catch (Exception e) {
                Log.error(Tag.CONN, "SCTP connection could not established: " + e.getMessage());
                return;
            }
            try {
                client.receiverLoop((receivedBytes, streamNumber)
                        -> handleSCTPMessage(msg.clientId, receivedBytes, streamNumber));
            } catch (Exception e) {
                Log.error(Tag.CONN, "SCTP connection error: " + e.getMessage());
            }
        });

        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(getThread()));
        receiverThread.start();
    }

    private void receiveSctpSendRequest(IwSctpSend msg) {
        var client = ctx.clients.get(msg.clientId);
        if (client == null) {
            Log.error(Tag.CONN, "SCTP client not found: " + msg.clientId);
            return;
        }
        client.send(msg.streamNumber, msg.data);
    }

    private void handleSCTPMessage(UUID clientId, byte[] receivedBytes, int streamNumber) {
        var pdu = NgapEncoding.decodeAper(receivedBytes);
        ctx.ngapTask.push(new IwNgapReceive(clientId, streamNumber, pdu));
    }
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.sctp;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.contexts.NgapAmfContext;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.itms.IwInitialSctpReady;
import tr.havelsan.ueransim.app.common.itms.IwNgapReceive;
import tr.havelsan.ueransim.app.common.itms.IwNgapSend;
import tr.havelsan.ueransim.app.common.itms.IwSctpAssociationSetup;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.sctp.ISctpAssociationHandler;
import tr.havelsan.ueransim.sctp.SctpAssociation;
import tr.havelsan.ueransim.sctp.SctpClient;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SctpTask extends NtsTask {

    private static final int NGAP_PROTOCOL_ID = 60;

    private final GnbSimContext ctx;
    private final HashMap<Guami, NgapAmfContext> amfs;

    private NtsTask ngapTask;
    private NtsTask appTask;

    public SctpTask(GnbSimContext ctx) {
        this.ctx = ctx;
        this.amfs = new HashMap<>();
    }

    @Override
    public void main() {
        ngapTask = ctx.nts.findTask(ItmsId.GNB_TASK_NGAP);
        appTask = ctx.nts.findTask(ItmsId.GNB_TASK_APP);

        if (ctx.ngapCtx.amfContexts.isEmpty()) {
            Log.error(Tag.CONFIG, "AMF contexts in GNB{%s} is empty", ctx.ctxId);
            return;
        }

        var setupCount = new AtomicInteger(0);

        for (var amf : ctx.ngapCtx.amfContexts.values()) {
            amfs.put(amf.guami, amf);

            var associationHandler = new ISctpAssociationHandler() {
                @Override
                public void onSetup(SctpAssociation sctpAssociation) {
                    amf.association = sctpAssociation;
                    ngapTask.push(new IwSctpAssociationSetup(amf.guami, sctpAssociation));
                    setupCount.incrementAndGet();
                }

                @Override
                public void onShutdown() {

                }
            };

            amf.sctpClient = new SctpClient(ctx.config.host, amf.host, amf.port, NGAP_PROTOCOL_ID, associationHandler);

            var receiverThread = new Thread(() -> {
                try {
                    amf.sctpClient.start();
                } catch (Exception e) {
                    Log.error(Tag.CONN, "SCTP connection could not established: " + e.getMessage());
                    return;
                }
                try {
                    amf.sctpClient.receiverLoop((receivedBytes, streamNumber)
                            -> handleSCTPMessage(amf.guami, receivedBytes, streamNumber));
                } catch (Exception e) {
                    Log.error(Tag.CONN, "SCTP connection error: " + e.getMessage());
                    return;
                }
            });

            Log.registerLogger(receiverThread, Log.getLoggerOrDefault(getThread()));

            receiverThread.start();
        }

        while (setupCount.get() != ctx.ngapCtx.amfContexts.size()) {
            // just wait
            Utils.sleep(100);
        }

        ctx.sim.triggerOnConnected(ctx, EConnType.SCTP); // TODO: Maybe for 'each' amf sctp connection
        appTask.push(new IwInitialSctpReady());

        while (true) {
            var msg = take();
            if (msg instanceof IwNgapSend) {
                var wrapper = (IwNgapSend) msg;
                amfs.get(wrapper.associatedAmf).sctpClient.send(wrapper.streamNumber, wrapper.data);
            }
        }
    }

    public void handleSCTPMessage(Guami associatedAmf, byte[] receivedBytes, int streamNumber) {
        var pdu = NgapEncoding.decodeAper(receivedBytes);
        ngapTask.push(new IwNgapReceive(associatedAmf, streamNumber, pdu));
    }
}

/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.air.AirNode;
import tr.havelsan.ueransim.app.app.listeners.INodeListener;
import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.simctx.AirSimContext;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.trigger.TriggeringWrapper;
import tr.havelsan.ueransim.app.common.trigger.TwOnConnected;
import tr.havelsan.ueransim.app.common.trigger.TwOnReceive;
import tr.havelsan.ueransim.app.common.trigger.TwOnSend;
import tr.havelsan.ueransim.app.gnb.GnbNode;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UeRanSim {

    private final HashMap<UUID, GnbSimContext> gnbMap;
    private final HashMap<UUID, UeSimContext> ueMap;
    private final AirSimContext airCtx;

    private final Thread triggeringThread;
    private final BlockingQueue<TriggeringWrapper> triggerQueue;
    private final List<INodeListener> nodeListeners;

    UeRanSim(List<INodeListener> nodeListeners) {
        this.gnbMap = new HashMap<>();
        this.ueMap = new HashMap<>();

        this.triggeringThread = new Thread(this::triggeringThreadMain);
        this.triggerQueue = new LinkedBlockingQueue<>();
        this.nodeListeners = new ArrayList<>(nodeListeners);

        this.airCtx = AirNode.createContext(this);

        triggeringThread.start();
        AirNode.run(airCtx);
    }

    public UeSimContext findUe(UUID id) {
        synchronized (this) {
            return ueMap.get(id);
        }
    }

    public GnbSimContext findGnb(UUID id) {
        synchronized (this) {
            return gnbMap.get(id);
        }
    }

    public GnbSimContext findGnbForUe(UUID gnbId) {
        GnbSimContext ctx;
        synchronized (this) {
            ctx = gnbMap.get(gnbId);
        }
        if (ctx == null) return null;
        if (!((GnbAppTask) (ctx.itms.findTask(ItmsId.GNB_TASK_APP))).isInitialSctpReady()) {
            return null;
        }
        return ctx;
    }

    public HashSet<UUID> allUes() {
        var res = new HashSet<UUID>();
        synchronized (this) {
            for (var item : ueMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public HashSet<UUID> allGnbs() {
        var res = new HashSet<UUID>();
        synchronized (this) {
            for (var item : gnbMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public AirSimContext getAirCtx() {
        return airCtx;
    }

    public UUID createGnb(GnbConfig config) {
        // TODO: Maybe check for unique node name

        var ctx = GnbNode.createContext(this, config);
        synchronized (this) {
            gnbMap.put(ctx.ctxId, ctx);
        }
        GnbNode.run(ctx);
        return ctx.ctxId;
    }

    public UUID createUe(UeConfig config) {
        // TODO: Maybe check for unique node name

        var ctx = UeNode.createContext(this, config);
        synchronized (this) {
            ueMap.put(ctx.ctxId, ctx);
        }
        UeNode.run(ctx);
        return ctx.ctxId;
    }

    //======================================================================================================
    //                                          TRIGGERS
    //======================================================================================================

    private void triggeringThreadMain() {
        Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

        while (true) {
            TriggeringWrapper obj;
            try {
                obj = triggerQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (var listener : nodeListeners) {
                if (obj instanceof TwOnConnected)
                    listener.onConnected(obj.ctx, ((TwOnConnected) obj).connType);
                if (obj instanceof TwOnSend)
                    listener.onSend(obj.ctx, ((TwOnSend) obj).msg);
                if (obj instanceof TwOnReceive)
                    listener.onReceive(obj.ctx, ((TwOnReceive) obj).msg);
            }
        }
    }

    public void triggerOnSend(BaseSimContext ctx, Object msg) {
        triggerQueue.add(new TwOnSend(ctx, msg));
    }

    public void triggerOnReceive(BaseSimContext ctx, Object msg) {
        triggerQueue.add(new TwOnReceive(ctx, msg));
    }

    public void triggerOnConnected(BaseSimContext ctx, INodeListener.ConnType connType) {
        triggerQueue.add(new TwOnConnected(ctx, connType));
    }
}
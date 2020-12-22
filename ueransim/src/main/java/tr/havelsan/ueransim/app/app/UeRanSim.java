/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.trigger.*;
import tr.havelsan.ueransim.app.gnb.GnbNode;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.utils.exceptions.SimException;

import java.util.*;

public class UeRanSim {

    private final HashMap<UUID, GnbSimContext> gnbMap;
    private final HashMap<UUID, UeSimContext> ueMap;

    private final HashMap<String, BaseSimContext> ctxByNodeName;

    private final List<MonitorTask> monitors;

    UeRanSim(List<MonitorTask> monitors) {
        this.gnbMap = new HashMap<>();
        this.ueMap = new HashMap<>();

        this.monitors = new ArrayList<>(monitors);
        for (var monitor : monitors) {
            monitor.start();
        }

        this.ctxByNodeName = new HashMap<>();
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

    @Deprecated
    public GnbSimContext findGnbForUe(UUID gnbId) {
        GnbSimContext ctx;
        synchronized (this) {
            ctx = gnbMap.get(gnbId);
        }
        if (ctx == null) return null;
        if (!ctx.nts.findTask(NtsId.GNB_TASK_APP, GnbAppTask.class).isInitialSctpReady()) {
            return null;
        }
        return ctx;
    }

    public UUID findContextIdByNodeName(String nodeName) {
        synchronized (this) {
            var ctx = ctxByNodeName.get(nodeName);
            return ctx != null ? ctx.ctxId : null;
        }
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

    public UUID createGnb(GnbConfig config) {
        var ctx = GnbNode.createContext(this, config);
        synchronized (this) {
            if (ctxByNodeName.containsKey(ctx.nodeName)) {
                throw new SimException("Another gNB with the same ID already exists. Please use another ID.");
            }

            gnbMap.put(ctx.ctxId, ctx);
            ctxByNodeName.put(ctx.nodeName, ctx);
        }
        GnbNode.run(ctx);
        return ctx.ctxId;
    }

    public UUID createUe(UeConfig config) throws SimException {
        UeSimContext ctx = UeNode.createContext(this, config);
        synchronized (this) {
            if (ctxByNodeName.containsKey(ctx.nodeName)) {
                throw new SimException("Another UE with the same IMSI already exists. Please use another IMSI.");
            }

            ueMap.put(ctx.ctxId, ctx);
            ctxByNodeName.put(ctx.nodeName, ctx);
        }
        UeNode.run(ctx);
        return ctx.ctxId;
    }

    //======================================================================================================
    //                                          TRIGGERS
    //======================================================================================================

    private void dispatchTrigger(TriggeringWrapper tw) {
        for (var monitor : monitors) monitor.push(tw);
    }

    public void triggerOnSend(BaseSimContext ctx, Object msg) {
        dispatchTrigger(new TwOnSend(ctx, msg));
    }

    public void triggerOnReceive(BaseSimContext ctx, Object msg) {
        dispatchTrigger(new TwOnReceive(ctx, msg));
    }

    public void triggerOnConnected(BaseSimContext ctx, EConnType connType) {
        dispatchTrigger(new TwOnConnected(ctx, connType));
    }

    public void triggerOnSwitch(BaseSimContext ctx, Enum<?> state) {
        dispatchTrigger(new TwOnSwitch(ctx, state));
    }
}
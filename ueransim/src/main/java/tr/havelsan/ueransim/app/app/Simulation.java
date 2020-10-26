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

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;

import java.util.HashSet;
import java.util.UUID;

public class Simulation {

    public static void registerUe(SimulationContext ctx, UeSimContext ue) {
        synchronized (ctx) {
            ctx.ueMap.put(ue.ctxId, ue);
        }
    }

    public static void registerGnb(SimulationContext ctx, GnbSimContext gnb) {
        synchronized (ctx) {
            ctx.gnbMap.put(gnb.ctxId, gnb);
        }
    }

    public static UeSimContext findUe(UeRanSim sim, UUID id) {
        var ctx = sim.getSimCtx();
        synchronized (ctx) {
            return ctx.ueMap.get(id);
        }
    }

    public static GnbSimContext findGnb(UeRanSim sim, UUID id) {
        var ctx = sim.getSimCtx();
        synchronized (ctx) {
            return ctx.gnbMap.get(id);
        }
    }

    public static void connectUeToGnb(UeSimContext ueContext, GnbSimContext gnbContext) {
        synchronized (ueContext) {
            synchronized (gnbContext) {
                ueContext.connectedGnb = gnbContext.ctxId;
            }
        }
    }


    public static HashSet<UUID> allUes(SimulationContext ctx) {
        var res = new HashSet<UUID>();
        synchronized (ctx) {
            for (var item : ctx.ueMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public static HashSet<UUID> allGnbs(SimulationContext ctx) {
        var res = new HashSet<UUID>();
        synchronized (ctx) {
            for (var item : ctx.gnbMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public static void triggerOnSend(BaseSimContext ctx, Object msg) {
        var listeners = ctx.sim.getSimCtx().messagingListeners;
        if (listeners != null) {
            for (var listener : listeners) {
                listener.onSend(ctx, msg);
            }
        }
    }

    public static void triggerOnReceive(BaseSimContext ctx, Object msg) {
        var listeners = ctx.sim.getSimCtx().messagingListeners;
        if (listeners != null) {
            for (var listener : listeners) {
                listener.onReceive(ctx, msg);
            }
        }
    }
}

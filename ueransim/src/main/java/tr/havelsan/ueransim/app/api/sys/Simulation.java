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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.app.api.sys;

import tr.havelsan.ueransim.app.core.BaseSimContext;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.events.BaseEvent;
import tr.havelsan.ueransim.app.events.gnb.GnbEvent;
import tr.havelsan.ueransim.app.events.ue.UeEvent;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

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

    static UeSimContext findUe(SimulationContext ctx, UUID id) {
        synchronized (ctx) {
            return ctx.ueMap.get(id);
        }
    }

    static GnbSimContext findGnb(SimulationContext ctx, UUID id) {
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

    public static void triggerOnSend(BaseSimContext<?> ctx, Object msg) {
        var listener = ctx.simCtx.nodeMessagingListener;
        if (listener != null) listener.onSend(ctx, msg);
    }

    public static void triggerOnReceive(BaseSimContext<?> ctx, Object msg) {
        var listener = ctx.simCtx.nodeMessagingListener;
        if (listener != null) listener.onReceive(ctx, msg);
    }

    public static void pushEvent(SimulationContext ctx, BaseEvent event) {
        // todo
    }

    public static void pushUeEvent(SimulationContext ctx, UUID ueId, UeEvent event) {
        UeSimContext ue;
        synchronized (ctx) {
            ue = findUe(ctx, ueId);
        }
        if (ue == null) {
            Logging.error(Tag.SYSTEM, "Simulation.pushUeEvent: could not find UE Sim Context with id: %s", ueId);
        } else {
            ue.pushEvent(event);
        }
    }

    public static void pushGnbEvent(SimulationContext ctx, UUID gnbId, GnbEvent event) {
        GnbSimContext gnb;
        synchronized (ctx) {
            gnb = findGnb(ctx, gnbId);
        }
        if (gnb == null) {
            Logging.error(Tag.SYSTEM, "Simulation.pushGnbEvent: could not find gNB Sim Context with id: %s", gnbId);
        } else {
            gnb.pushEvent(event);
        }
    }
}

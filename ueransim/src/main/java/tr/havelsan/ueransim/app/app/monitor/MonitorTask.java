/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.monitor;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.trigger.*;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

public abstract class MonitorTask extends NtsTask {

    public MonitorTask() {
        super(true);
    }

    @Override
    public final void main() {
        Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

        while (true) {
            var obj = take();
            if (obj instanceof TriggeringWrapper) {
                var w = (TriggeringWrapper) obj;

                if (w instanceof TwOnConnected)
                    onConnected(w.ctx, ((TwOnConnected) w).connType);
                else if (w instanceof TwOnSend)
                    onSend(w.ctx, ((TwOnSend) w).msg);
                else if (w instanceof TwOnReceive)
                    onReceive(w.ctx, ((TwOnReceive) w).msg);
                else if (w instanceof TwOnSwitch)
                    onSwitched(w.ctx, ((TwOnSwitch) w).state);
            }
        }
    }

    /**
     * Triggered when a simulation node has established a connection.
     */
    protected abstract void onConnected(BaseSimContext ctx, EConnType connType);

    /**
     * Triggered when a simulation node has send a message.
     * WARNING: Do not mutate any of the parameters.
     */
    protected abstract void onSend(BaseSimContext ctx, Object message);

    /**
     * Triggered when a simulation node has received a message.
     * WARNING: Do not mutate any of the parameters.
     */
    protected abstract void onReceive(BaseSimContext ctx, Object message);

    /**
     * Triggered when a simulation node has switched to another state.
     */
    protected abstract void onSwitched(BaseSimContext ctx, Enum<?> state);
}

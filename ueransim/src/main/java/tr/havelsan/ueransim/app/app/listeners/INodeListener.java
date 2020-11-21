/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.listeners;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public interface INodeListener {

    enum Type {
        SCTP,
        ANY_IPv4,
        UE_MR_GNB,
        TUN_BRIDGE
    }

    /**
     * Triggered when a simulation node has established a connection.
     */
    void onConnected(BaseSimContext ctx, Type connectionType);

    /**
     * Triggered when a simulation node has send a message.
     * WARNING: Do not mutate any of the parameters.
     */
    void onSend(BaseSimContext ctx, Object message);

    /**
     * Triggered when a simulation node has received a message.
     * WARNING: Do not mutate any of the parameters.
     */
    void onReceive(BaseSimContext ctx, Object message);
}

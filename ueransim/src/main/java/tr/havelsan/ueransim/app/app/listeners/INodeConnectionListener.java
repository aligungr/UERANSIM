/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.listeners;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public interface INodeConnectionListener {

    enum Type {
        SCTP,
        ANY_IPv4,
        UE_MR_GNB,
        TUN_BRIDGE
    }

    void onConnected(BaseSimContext ctx, Type connectionType);
}

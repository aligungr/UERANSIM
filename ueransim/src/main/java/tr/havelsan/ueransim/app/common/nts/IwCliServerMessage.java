/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.util.UUID;

public class IwCliServerMessage {
    public static final UUID BROADCAST = UUID.randomUUID();

    public final UUID client;
    public final byte[] data;

    public IwCliServerMessage(UUID client, byte[] data) {
        this.client = client;
        this.data = data;
    }
}

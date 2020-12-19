/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.util.UUID;

public class IwSctpConnectionRequest {
    public final UUID clientId;
    public final String address;
    public final int port;

    public IwSctpConnectionRequest(UUID clientId, String address, int port) {
        this.clientId = clientId;
        this.address = address;
        this.port = port;
    }
}

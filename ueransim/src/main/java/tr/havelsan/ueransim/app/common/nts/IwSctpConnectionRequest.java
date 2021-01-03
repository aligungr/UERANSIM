/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.util.UUID;

public class IwSctpSend {
    public final int streamNumber;
    public final byte[] data;
    public final UUID clientId;

    public IwSctpSend(int streamNumber, byte[] data, UUID clientId) {
        this.streamNumber = streamNumber;
        this.data = data;
        this.clientId = clientId;
    }
}

/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.net.InetAddress;

public class IwGtpDownlink {
    public final OctetString data;
    public final InetAddress address;
    public final int port;

    public IwGtpDownlink(OctetString data, InetAddress address, int port) {
        this.data = data;
        this.address = address;
        this.port = port;
    }
}

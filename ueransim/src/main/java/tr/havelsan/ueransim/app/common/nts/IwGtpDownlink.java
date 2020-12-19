/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

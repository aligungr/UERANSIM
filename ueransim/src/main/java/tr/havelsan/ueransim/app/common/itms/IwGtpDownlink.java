package tr.havelsan.ueransim.app.common.itms;

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

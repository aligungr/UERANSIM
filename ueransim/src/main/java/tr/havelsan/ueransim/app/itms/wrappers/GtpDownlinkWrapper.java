package tr.havelsan.ueransim.app.itms.wrappers;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.net.InetAddress;

public class GtpDownlinkWrapper {
    public final OctetString data;
    public final InetAddress address;
    public final int port;

    public GtpDownlinkWrapper(OctetString data, InetAddress address, int port) {
        this.data = data;
        this.address = address;
        this.port = port;
    }
}

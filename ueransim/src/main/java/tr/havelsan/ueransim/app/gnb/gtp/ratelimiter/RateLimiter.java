package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

import tr.havelsan.ueransim.app.common.PduSessionResource;
import tr.havelsan.ueransim.utils.octets.OctetString;

public interface RateLimiter {
    void handleDownlinkPacket(PduSessionResource pduSession, OctetString ipPacket);
    void handleUplinkPacket(PduSessionResource pduSession, OctetString ipPacket);
}

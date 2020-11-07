package tr.havelsan.ueransim.app.common.itms;

import java.util.UUID;

public class IwTunnelCreateSampleInfo {
    public final UUID ueId;
    public final int pduSessionId;

    public IwTunnelCreateSampleInfo(UUID ueId, int pduSessionId) {
        this.ueId = ueId;
        this.pduSessionId = pduSessionId;
    }
}
